package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.CompartilhaDAO;
import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Compartilha;
import com.ifpb.edu.model.domain.publicacao.Publicacao;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class CompartilhaDAOImpDB implements CompartilhaDAO {
	
	private Connection connection;	

	public CompartilhaDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void cria(Compartilha compartilha) throws DataAccessException {
		try {
		String query = "INSERT INTO compartilha (usuarioid,publicacao,grupoid) "
				+ "VALUES (?,?,?) ";
		PreparedStatement stm = connection.prepareStatement(query);
		stm.setInt(1, compartilha.getUsuario().getId());
		stm.setInt(2, compartilha.getPublicacao().getId());
		stm.setInt(3, compartilha.getGrupo().getId());
		stm.execute();
		}catch (Exception e) {
			throw new DataAccessException("Falha ao compartilhar publicação.");	
		}
	}

	@Override
	public void exclui(Compartilha compartilha) throws DataAccessException {
		try {
			String query = "DELETE FROM compartilha "
					+ " WHERE (usuario.id = ?) AND "
					+ " (publicacaoid = ?) AND "
					+ " (grupoid = ?)";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, compartilha.getUsuario().getId());
			stm.setInt(2, compartilha.getPublicacao().getId());
			stm.setInt(3, compartilha.getGrupo().getId());
			stm.execute();
		}catch (Exception e) {
			throw new DataAccessException("Falha ao remover compartilhamento");
		}

	}

	@Override
	public List<Publicacao> lista() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publicacao> lista(Grupo grupo) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publicacao> lista(Usuario usuario) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
