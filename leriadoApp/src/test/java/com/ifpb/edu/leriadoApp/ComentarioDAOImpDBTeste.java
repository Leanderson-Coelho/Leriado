package com.ifpb.edu.leriadoApp;

import java.util.List;

import org.junit.Test;

import com.ifpb.edu.model.dao.publicacao.ComentarioDAO;
import com.ifpb.edu.model.dao.publicacao.impdb.ComentarioDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.TextoDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Comentario;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class ComentarioDAOImpDBTeste {
	
	@Test
	public void criaTeste() {
		ComentarioDAOImpDB comentarioDAO = new ComentarioDAOImpDB();
		Texto texto = new Texto();		
		Usuario usuario = new Usuario();
		texto.setId(3);
		usuario.setId(1);
		Comentario comentario = new Comentario("Isso é um teste de comentário", usuario, texto);
		try {
			comentarioDAO.cria(comentario);
		} catch (DataAccessException e) {		
			e.printStackTrace();
		}		
	}
	
	@Test
	public void listaTeste() {		
		List<Comentario> comentarios = null;
		ComentarioDAOImpDB comentarioDAO = new ComentarioDAOImpDB();
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();		
		try {
			Texto texto = textoDAO.buscar(1).orElseThrow();			
			comentarios = comentarioDAO.lista(texto,0,2);
			System.out.println("Texto:");
			System.out.println(texto.getConteudo());
			for (Comentario comentario : comentarios) {
				System.out.println(comentario.getConteudo());
				System.out.println(comentario.getUsuario().getNome());				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void quantTeste() {
		try {
			Texto texto = new Texto();
			texto.setId(1);
			System.out.println(new ComentarioDAOImpDB().quant(texto));
		}catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void excluirTeste() {
		ComentarioDAOImpDB comentarioDAO = new ComentarioDAOImpDB();
		Comentario comentario = new Comentario();
		comentario.setId(11);
		try {
			comentarioDAO.exclui(comentario);						
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
