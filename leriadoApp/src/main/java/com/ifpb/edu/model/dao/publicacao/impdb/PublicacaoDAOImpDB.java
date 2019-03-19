package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.PublicacaoDAO;
import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.publicacao.Publicacao;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class PublicacaoDAOImpDB implements PublicacaoDAO {

	private Connection connection;

	public PublicacaoDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	private void lerTabela(Publicacao publicacao, ResultSet rs) throws DataAccessException, SQLException {
		new TextoDAOImpDB().buscar(rs.getInt("textoid"), publicacao);
		publicacao.setRelevancia(rs.getInt("relevancia"));
	}

	@Override
	public void cria(Publicacao publicacao) throws DataAccessException {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			textoDAO.cria(publicacao);
			String query = "INSERT INTO publicacao (textoid,relevancia) VALUES (?,?)";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, publicacao.getId());
			stm.setInt(2, publicacao.getRelevancia());
			stm.execute();
		} catch (Exception e) {
			throw new DataAccessException("Falha ao criar publicação");
		}
	}

	@Override
	public void edita(Publicacao publicacao) throws DataAccessException {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			if (textoDAO.tipo(publicacao) != TipoTexto.PUBLICACAO)
				throw new Exception();
			textoDAO.edita(publicacao);
			String query = "UPDATE publicacao SET " + "relevancia = ? " + "WHERE textoid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, publicacao.getRelevancia());
			stm.setInt(2, publicacao.getId());
			stm.execute();
		} catch (Exception e) {
			throw new DataAccessException("Falha ao editar publicação");
		}

	}

	@Override
	public void exclui(Publicacao publicacao) throws DataAccessException {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			if (textoDAO.tipo(publicacao) != TipoTexto.PUBLICACAO)
				throw new Exception();
			textoDAO.exclui(publicacao);
		} catch (Exception e) {
			throw new DataAccessException("Falha ao excluir publicação");
		}
	}

	@Override
	public Publicacao buscar(int id) throws DataAccessException {
		Publicacao publicacao = new Publicacao();
		publicacao.setId(id);
		buscar(publicacao);
		return publicacao;
	}

	@Override
	public void buscar(Publicacao publicacao) throws DataAccessException {
		try {
			int id = publicacao.getId();
			buscar(id, publicacao);
		} catch (Exception e) {
			throw new DataAccessException("Falha ao buscar publicação");
		}
	}

	@Override
	public void buscar(int id, Publicacao publicacao) throws DataAccessException {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			textoDAO.buscar(id, publicacao);
			if (textoDAO.tipo(publicacao) == TipoTexto.FALHA)
				throw new Exception();
			String query = "SELECT * FROM publicacao " + "WHERE textoid = ?";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				lerTabela(publicacao, rs);
			} else
				throw new Exception();
		} catch (Exception e) {
			throw new DataAccessException("Falha ao buscar publicação");
		}
	}

	@Override
	public int quant() throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM texto " + "WHERE TipoTexto(id) = 'PUBLICACAO' ";
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar a quantidade de publicações");
		}
		return 0;
	}

	@Override
	public List<Publicacao> lista() throws DataAccessException {
		List<Publicacao> publicacoes = new ArrayList<Publicacao>();
		try {
			String query = "SELECT * FROM publicacao " + "WHERE TipoTexto(textoid) = 'PUBLICACAO' ";
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Publicacao publicacao = new Publicacao();
				lerTabela(publicacao, rs);
				publicacoes.add(publicacao);
			}
		} catch (Exception e) {
			throw new DataAccessException("Falha ao listar publicações");
		}
		return publicacoes;
	}

	@Override
	public List<Publicacao> lista(int inicio, int quant) throws DataAccessException {
		List<Publicacao> publicacoes = new ArrayList<Publicacao>();
		try {
			String query = "SELECT * FROM publicacao " + "WHERE TipoTexto(textoid) = 'PUBLICACAO' "
					+ " OFFSET ? LIMIT ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, inicio);
			stm.setInt(2, quant);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Publicacao publicacao = new Publicacao();
				lerTabela(publicacao, rs);
				publicacoes.add(publicacao);
			}
		} catch (Exception e) {
			throw new DataAccessException("Falha ao listar publicações");
		}
		return publicacoes;
	}

}
