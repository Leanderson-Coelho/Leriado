package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.dao.publicacao.MarcaDAO;
import com.ifpb.edu.model.domain.Usuario;
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
		try {
			String query = "SELECT EXISTS "
					+ "(SELECT FROM marca WHERE (textoid = ?) AND (usuarioid = ?))";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, textoId);
			stm.setInt(2, usuarioId);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getBoolean(1); 
			}			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao consultar marca");
		}
		return false;
	}

	@Override
	public List<Marca> listaMarca(Texto texto) throws DataAccessException {
		List<Marca> marcacoes = new ArrayList<Marca>();
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		UsuarioDaoImpl usuarioDAO = new UsuarioDaoImpl();
		try {
			int ti = texto.getId();
			texto = textoDAO.buscar(ti).orElseThrow();
			String query = "SELECT usuarioid FROM marca "
					+ "WHERE  textoid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, ti);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				marcacoes.add(new Marca(texto, usuarioDAO.buscarPorId(rs.getInt("usuarioid"))));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar marcações");
		}
		return marcacoes;
	}

}
