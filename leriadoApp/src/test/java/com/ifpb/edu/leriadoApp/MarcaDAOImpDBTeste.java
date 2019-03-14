package com.ifpb.edu.leriadoApp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import org.junit.Test;

import com.ifpb.edu.model.dao.publicacao.impdb.MarcaDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.TextoDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Marca;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class MarcaDAOImpDBTeste {
	
	@Test
	public void CriaTeste() {
		MarcaDAOImpDB marcaDAO = new MarcaDAOImpDB();
		Texto texto = new Texto();
		Usuario usuario = new Usuario();		
		Marca marca = new Marca();
		texto.setId(4);
		usuario.setId(1);
		marca.setTexto(texto);
		marca.setUsuario(usuario);
		try {
			marcaDAO.cria(marca);			
		}catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	@Test
	public void ExisteTeste() {
		MarcaDAOImpDB marcaDAO = new MarcaDAOImpDB();
		try {
			System.out.println(marcaDAO.existe(4, 2));
		} catch (DataAccessException e) {		
			e.printStackTrace();
		}
	}
	
	@Test
	public void ListaTeste() {
		MarcaDAOImpDB marcaDAO = new MarcaDAOImpDB();		
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			Texto texto = textoDAO.buscar(1).orElseThrow(null);
			System.out.println(texto.getConteudo());
			List<Marca> marcacoes = marcaDAO.listaMarca(texto);
			for (Marca marca : marcacoes) {
				System.out.println(marca.getUsuario().getNome());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
