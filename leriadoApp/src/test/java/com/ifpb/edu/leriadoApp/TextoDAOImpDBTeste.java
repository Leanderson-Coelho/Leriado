package com.ifpb.edu.leriadoApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.dao.publicacao.impdb.TextoDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class TextoDAOImpDBTeste {
	
	private TextoDAOImpDB textoDAO = null;
	private Usuario usuario = null;
	private Texto texto = null;
	
	@Before
	public void inicioTeste() {
		textoDAO = new TextoDAOImpDB();
		usuario = new Usuario();
		usuario.setId(1);
		texto = new Texto("Conteúdo de teste", usuario);		
	}
	
	@Test
	public void criaTextoTeste() {	
		try {
			assertNotEquals(0, textoDAO.cria(texto));
			System.out.println(texto);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void editaTextoTeste() {				
		try {

			Texto texto = textoDAO.buscar(3).orElseThrow();
			texto.setConteudo("Modificação do texto");
			textoDAO.edita(texto);
		} catch (DataAccessException e) {		
			e.printStackTrace();
		}		
	}
	
	@Test
	public void excluiTextoTeste() {		
		try {			
			texto.setId(1);
			textoDAO.exclui(texto);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void listarTextoTeste() {
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
		try {
			System.out.println(textoDAO.quant());
		}catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void tipoTextoTeste() {		
		try {
			List<Texto> textos = textoDAO.lista();
			for (Texto texto : textos) {				
				System.out.println(texto.getConteudo());
				System.out.println(textoDAO.tipo(texto));
				System.out.println(texto.getTipoTexto());
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
