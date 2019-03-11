package com.ifpb.edu.leriadoApp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.dao.publicacao.impdb.FotoDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.NoticiaDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Foto;
import com.ifpb.edu.model.domain.publicacao.Noticia;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class NoticiaDAOImpDBTeste {
	
	private NoticiaDAOImpDB noticiaDAO = null;
	private Noticia noticia = null;
	private Usuario usuario = null;	
	private List<Foto> fotos = null;
	
	@Before
	public void inicioTeste() {		
		noticiaDAO = new NoticiaDAOImpDB();
		fotos = new ArrayList<Foto>();
		try {
			usuario = new UsuarioDaoImpl().buscarPorId(1);
			FotoDAOImpDB fotosDAO = new FotoDAOImpDB(); 
			fotos.add(fotosDAO.buscar(7));
			fotos.add(fotosDAO.buscar(8));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		noticia = new Noticia("Título de teste da notícia", "Conteúdo de teste da notícia", usuario, 1, fotos);
	}
	
	@Test
	public void criaTeste() {
		try {
			noticiaDAO.cria(noticia);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void excluiTeste() {
		noticia.setId(22);
		try {
			noticiaDAO.exclui(noticia);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
