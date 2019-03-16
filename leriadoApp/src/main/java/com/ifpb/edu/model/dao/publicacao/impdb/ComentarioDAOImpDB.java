package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.ComentarioDAO;
import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.publicacao.Comentario;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class ComentarioDAOImpDB implements ComentarioDAO {

	private Connection connection;

	public ComentarioDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void cria(Comentario comentario) throws DataAccessException {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		Texto texto = null;
		try {
			texto = textoDAO.buscar(comentario.getResponde().getId()).orElseThrow(null);
			comentario.setResponde(texto);
			textoDAO.cria(comentario);
			String query = "INSERT INTO comentario (textoid,respondeid) " + "VALUES (?,?)";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, comentario.getId());
			stm.setInt(2, texto.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("Falha ao criar comentário");
		}
	}

	@Override
	public void edita(Comentario comentario) throws DataAccessException {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			textoDAO.edita(comentario);
		} catch (Exception e) {
			throw new DataAccessException("Falha ao editar comentário");
		}
	}

	@Override
	public void exclui(Comentario comentario) throws DataAccessException {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			if (textoDAO.tipo(comentario) != TipoTexto.COMENTARIO)
				throw new Exception();
			textoDAO.exclui(comentario);
		} catch (Exception e) {
			throw new DataAccessException("Falha ao excluir comentário");
		}
	}

	@Override
	public int quant(Texto texto) throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM comentario " + "WHERE respondeid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, texto.getId());
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar a quantidade de comentários");
		}
		return 0;
	}

	@Override
	public List<Comentario> lista(Texto texto) throws DataAccessException {
		List<Comentario> cometarios = new ArrayList<Comentario>();
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			int it = texto.getId();
			texto = textoDAO.buscar(it).orElseThrow(null);
			String query = "SELECT textoid FROM comentario " + "WHERE respondeid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, texto.getId());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Comentario comentario = new Comentario();
				textoDAO.buscar(rs.getInt("textoid"), comentario);
				comentario.setResponde(texto);
				cometarios.add(comentario);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("Falha ao listar comentários");
		}
		return cometarios;
	}

	@Override
	public List<Comentario> lista(Texto texto, int inicio, int quant) throws DataAccessException {
		List<Comentario> cometarios = new ArrayList<Comentario>();
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			int it = texto.getId();
			texto = textoDAO.buscar(it).orElseThrow(null);
			String query = "SELECT textoid FROM comentario " + "WHERE respondeid = ? " + "OFFSET ? LIMIT ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, texto.getId());
			stm.setInt(2, inicio);
			stm.setInt(3, quant);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Comentario comentario = new Comentario();
				textoDAO.buscar(rs.getInt("textoid"), comentario);
				comentario.setResponde(texto);
				cometarios.add(comentario);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("Falha ao listar comentários");
		}
		return cometarios;
	}

}
