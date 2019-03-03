package com.ifpb.edu.leriadoApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.ifpb.edu.model.dao.publicacao.impdb.TextoDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class TextoDAOImpDBTeste {
	
	@Test
	public void criaTextoTeste() {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		Usuario usuario = new Usuario();
		usuario.setId(1);
		Texto texto = new Texto("Conteúdo",usuario);
		try {
			assertNotEquals(0, textoDAO.cria(texto));
			System.out.println(texto);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void editaTextoTeste() {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();		
		try {
			Texto texto = textoDAO.buscar(3).orElse(null);
			texto.setConteudo("Modificação do texto");
			textoDAO.edita(texto);
		} catch (DataAccessException e) {		
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
