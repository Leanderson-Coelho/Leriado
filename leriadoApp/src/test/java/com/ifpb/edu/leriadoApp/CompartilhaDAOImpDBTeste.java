package com.ifpb.edu.leriadoApp;

import java.time.LocalDateTime;

import org.junit.Test;

import com.ifpb.edu.model.dao.publicacao.impdb.CompartilhaDAOImpDB;
import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Compartilha;
import com.ifpb.edu.model.domain.publicacao.Publicacao;

public class CompartilhaDAOImpDBTeste {
	
	@Test
	public void criaTeste() {
		CompartilhaDAOImpDB compartilhaDAO = new CompartilhaDAOImpDB(); 
		Publicacao publicacao = new Publicacao();
		Usuario usuario = new Usuario();
		Grupo grupo = new Grupo();
		publicacao.setId(1);
		usuario.setId(3);
		grupo.setId(1);	
		try {			
			compartilhaDAO.cria(new Compartilha(LocalDateTime.now(), usuario, publicacao, grupo));					
		}catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	@Test
	public void excluiTeste() {
		CompartilhaDAOImpDB compartilhaDAO = new CompartilhaDAOImpDB(); 
		Publicacao publicacao = new Publicacao();
		Usuario usuario = new Usuario();
		Grupo grupo = new Grupo();
		publicacao.setId(1);
		usuario.setId(3);
		grupo.setId(1);	
		try {			
			compartilhaDAO.exclui(new Compartilha(LocalDateTime.now(), usuario, publicacao, grupo));					
		}catch (Exception e) {			
			e.printStackTrace();
		}
	}
	

}
