package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.MarcaDAO;
import com.ifpb.edu.model.domain.publicacao.Marca;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class MarcaDAOImpDB implements MarcaDAO{
	
	private Connection connection;
	
	public MarcaDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void cria(Marca marca) throws DataAccessException {
		try {
			String query = "INSERT INTO marca (textoid, usuarioid) VALUES (?,?) ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, marca.getTexto().getId());
			stm.setInt(2, marca.getUsuario().getId());
			stm.executeUpdate();			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao marcar usuário");
		}
		
	}
	
	@Override
	public void exclui(Marca marca) throws DataAccessException {
		try {
			String query = "DELETE FROM marca "
					+ "WHERE (textoid = ?) AND (ususarioid = ?) ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, marca.getTexto().getId());
			stm.setInt(2, marca.getUsuario().getId());
			stm.executeUpdate();
		}catch (Exception e) {
			throw new DataAccessException("Falha ao excluir marca.");
		}
		
	}

	@Override
	public boolean existe(int textoId, int usuarioId) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Marca> listaMarca(Texto texto) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
