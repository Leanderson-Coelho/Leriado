package com.ifpb.edu.leriadoApp;

import java.util.List;

import javax.sound.midi.Soundbank;

import org.junit.Test;

import com.ifpb.edu.model.dao.publicacao.impdb.PublicacaoDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.TextoDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Publicacao;

public class PublicacaoDAOImpDBTeste {
	
	@Test
	public void criaTeste() {
		Usuario usuario = new Usuario();
		usuario.setId(1);
		Publicacao publicacao = new Publicacao("Teste de criação de publicacao", usuario, 1);
		PublicacaoDAOImpDB publicacaoDAO = new PublicacaoDAOImpDB();
		try{
			publicacaoDAO.cria(publicacao);			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void editaTeste() {
		Publicacao publicacao = new Publicacao();
		PublicacaoDAOImpDB publicaoDAO = new PublicacaoDAOImpDB();
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		publicacao.setId(1);
		try {
			textoDAO.buscar(publicacao);
			publicacao.setConteudo("Modificação do conteúdo da públicacao");
			publicaoDAO.edita(publicacao);			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void excluiTeste() {
		Publicacao publicacao = new Publicacao();
		PublicacaoDAOImpDB publicaoDAO = new PublicacaoDAOImpDB();
		publicacao.setId(12);
		try {
			publicaoDAO.exclui(publicacao);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void quantTeste() {
		try {
			System.out.println(new PublicacaoDAOImpDB().quant());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void listaTeste() {
		List<Publicacao> publicacoes = null;
		PublicacaoDAOImpDB publicacaoDAO = new PublicacaoDAOImpDB();		
		try {
			publicacoes = publicacaoDAO.lista(0,2);
			for (Publicacao publicacao : publicacoes) {
				System.out.println(publicacao.getConteudo());				
				System.out.println("----------");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
