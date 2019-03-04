package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import com.ifpb.edu.model.dao.publicacao.ComentarioDAO;
import com.ifpb.edu.model.domain.publicacao.Comentario;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class ComentarioDAOImpDB implements ComentarioDAO{
	
	private Connection connection;	

	public ComentarioDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void cria(Comentario comentario) throws DataAccessException {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		Texto texto = null;
		try {
			texto = textoDAO.buscar(comentario.getResponde().getId()).orElseThrow();
			comentario.setResponde(texto);
			textoDAO.cria(comentario);
			String query = "INSERT INTO comentario (textoid,respondeid) "
					+ "VALUES (?,?)";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, comentario.getId());
			stm.setInt(2, texto.getId());
			stm.execute();
		}catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("Falha ao criar comentário");
		}
	}

	@Override
	public void edita(Comentario comentario) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exclui(Comentario comentario) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Comentario> buscar(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int quant(Texto texto) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Comentario> lista(Texto texto) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comentario> lista(Texto texto, int inicio, int quant) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
