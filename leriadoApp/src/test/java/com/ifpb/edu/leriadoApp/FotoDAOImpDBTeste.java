package com.ifpb.edu.leriadoApp;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.dao.publicacao.impdb.FotoDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Foto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class FotoDAOImpDBTeste {
	
	private FotoDAOImpDB fotoDAO = null;
	private Usuario usuario = null;
	private Foto foto = null;	
	
	@Before
	public void inicio() {
		fotoDAO = new FotoDAOImpDB();
		try {
			usuario = new UsuarioDaoImpl().buscarPorId(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		foto = new Foto("Isso é um teste para criar foto",usuario,1,"Arquivo de teste");
	}
	
	@Test
	public void criaTeste() {
		try {
			fotoDAO.cria(foto);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void excluiTeste() {
		foto.setId(17);
		try {
			fotoDAO.exclui(foto);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void buscaTeste() {
		try {
			foto = fotoDAO.buscar(18);
			System.out.println(foto.getConteudo());
			System.out.println(foto.getArquivo());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
	}

}
