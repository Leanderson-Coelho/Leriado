package com.ifpb.edu.leriadoApp;

import org.junit.Before;
import org.junit.Test;

import com.ifpb.edu.model.dao.publicacao.impdb.LinkDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Link;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class LinkDAOImpDBTeste {
	
	private LinkDAOImpDB linkDAO = null;
	private Link link = null;
	private Usuario usuario = null;
	
	@Before
	public void inicio() {
		linkDAO = new LinkDAOImpDB();
		usuario = new Usuario();
		usuario.setId(1);		
		link = new Link("Conteúdo de teste para o linkk", usuario, 1, "Link para o teste");
	}
	
	@Test
	public void criaTeste() {
		try {
			linkDAO.cria(link);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
				
	}
	
	@Test
	public void excluiTeste() {
		link.setId(12);
		try {
			linkDAO.exclui(link);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void quantTeste() {
		try {
			System.out.println(linkDAO.quant());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
