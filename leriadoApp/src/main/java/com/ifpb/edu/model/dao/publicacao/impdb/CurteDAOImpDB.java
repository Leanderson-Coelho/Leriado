package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.CurteDAO;
import com.ifpb.edu.model.domain.publicacao.Curte;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class CurteDAOImpDB implements CurteDAO{
	
	private Connection connection;
		
	public CurteDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void criaCurte(Curte curte) throws DataAccessException{
		try {
			String query = "INSERT INTO curte (textoid,usuarioid) "
					+ "VALUES(?,?)";
			PreparedStatement stm =  connection.prepareStatement(query);
			stm.setInt(1,curte.getTexto().getId());
			stm.setInt(2,curte.getUsuario().getId());			
			stm.executeUpdate();
			}catch (Exception e) {
				throw new DataAccessException("Falha ao criar curtida");
			}		
	}
	
	@Override
	public void excluiCurte(Curte curte) throws DataAccessException{
		try {
			String query = "DELETE FROM curte"
					+ "WHERE (textoid=?) AND (usuarioid=?) ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1,curte.getTexto().getId());
			stm.setInt(2, curte.getUsuario().getId());
			stm.executeUpdate();			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao excluir curtida");
		}		
	}

	@Override
	public int quantCurtidas(Texto texto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Curte> listaCurte(Texto texto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
