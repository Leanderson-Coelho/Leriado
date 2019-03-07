package com.ifpb.edu.leriadoApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;

import com.ifpb.edu.model.dao.publicacao.TipoTexto;
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
			Texto texto = textoDAO.buscar(3).orElse();
			texto.setConteudo("Modificação do texto");
			textoDAO.edita(texto);
		} catch (DataAccessException e) {		
			e.printStackTrace();
		}		
	}
	
	@Test
	public void excluiTextoTeste() {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			Texto texto = new Texto();
			texto.setId(1);
			textoDAO.exclui(texto);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void listarTextoTeste() {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			List<Texto> textos = textoDAO.lista(10,3);
			for (Texto texto : textos) {
				System.out.println(texto.getUsuario().getNome());
				System.out.println(texto.getConteudo());				
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void quantTextoTeste() {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			System.out.println(textoDAO.quant());
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	@Test
	public void tipoTextoTeste() {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			List<Texto> textos = textoDAO.lista();
			for (Texto texto : textos) {				
				System.out.println(texto.getConteudo());
				System.out.println(textoDAO.tipo(texto));
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
