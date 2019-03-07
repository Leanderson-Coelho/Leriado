package com.ifpb.edu.leriadoApp;

import java.time.LocalDateTime;
import java.util.List;

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
	
	@Test
	public void quantTeste() {
		CompartilhaDAOImpDB compartilhaDAO = new CompartilhaDAOImpDB(); 
		Publicacao publicacao = new Publicacao();
		Usuario usuario = new Usuario();
		Grupo grupo = new Grupo();
		publicacao.setId(1);
		usuario.setId(1);
		grupo.setId(1);	
		try {
			System.out.println(compartilhaDAO.quant(grupo));					
		}catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	@Test
	public void listaTeste() {
		CompartilhaDAOImpDB compartilhaDAO = new CompartilhaDAOImpDB(); 
		Publicacao publicacao = new Publicacao();
		Usuario usuario = new Usuario();
		Grupo grupo = new Grupo();
		publicacao.setId(1);
		usuario.setId(1);
		grupo.setId(1);	
		try {
			List<Compartilha> comp = compartilhaDAO.lista(grupo,0,1);
			for (Compartilha compartilha : comp) {
				System.out.print("Grupo: ");
				System.out.println(compartilha.getGrupo().getNome());
				System.out.print("Texto: ");
				System.out.println(compartilha.getPublicacao().getConteudo());
				System.out.print("Usuario: ");
				System.out.println(compartilha.getUsuario().getNome());
				System.out.print("Data: ");
				System.out.println(compartilha.getDataHora());				
			}
								
		}catch (Exception e) {			
			e.printStackTrace();
		}
	}
	

}