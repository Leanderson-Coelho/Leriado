package com.ifpb.edu.leriadoApp;

import org.junit.Test;

import com.ifpb.edu.model.dao.publicacao.impdb.MarcaDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Marca;
import com.ifpb.edu.model.domain.publicacao.Texto;

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
}
