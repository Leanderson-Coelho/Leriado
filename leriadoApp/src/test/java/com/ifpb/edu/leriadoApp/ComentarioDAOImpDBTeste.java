package com.ifpb.edu.leriadoApp;

import org.junit.Test;

import com.ifpb.edu.model.dao.publicacao.ComentarioDAO;
import com.ifpb.edu.model.dao.publicacao.impdb.ComentarioDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Comentario;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class ComentarioDAOImpDBTeste {
	
	@Test
	public void criaTeset() {
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

}
