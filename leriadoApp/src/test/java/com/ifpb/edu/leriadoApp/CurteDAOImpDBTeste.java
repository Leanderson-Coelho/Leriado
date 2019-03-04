package com.ifpb.edu.leriadoApp;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.ifpb.edu.model.dao.publicacao.impdb.CurteDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Curte;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class CurteDAOImpDBTeste {
	
	
	@Test
	public void criaTeste() {
		CurteDAOImpDB curteDAO = new CurteDAOImpDB();
		Texto texto = new Texto();
		Usuario usuario = new Usuario();
		try {
			texto.setId(1);
			usuario.setId(3);
			curteDAO.cria(new Curte(texto, usuario, LocalDateTime.now()));
		}catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void listaTeste() {
		CurteDAOImpDB curteDAO = new CurteDAOImpDB();
		Texto texto = new Texto();
		texto.setId(1);
		List<Curte> curtidas=null;
		try {
			curtidas = curteDAO.lista(texto);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		for (Curte curte : curtidas) {
			System.out.println(curte.getTexto().getConteudo());
			System.out.println(curte.getUsuario().getNome());			
		}
	}
	
	@Test
	public void quantTeste() {
		CurteDAOImpDB curteDAO = new CurteDAOImpDB();
		Texto texto = new Texto();
		texto.setId(1);
		try {
			System.out.println(curteDAO.quant(texto));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void excluiTeste() {
		CurteDAOImpDB curteDAO = new CurteDAOImpDB();
		Texto texto = new Texto();
		texto.setId(1);
		List<Curte> curtidas=null;
		try {
			System.out.println(curteDAO.quant(texto));
			curtidas = curteDAO.lista(texto);
			curteDAO.exclui(curtidas.get(1));
			System.out.println(curteDAO.quant(texto));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void existeTeste() {
		CurteDAOImpDB curteDAO = new CurteDAOImpDB();
		try {
			System.out.println(curteDAO.existe(1, 1));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}

}
