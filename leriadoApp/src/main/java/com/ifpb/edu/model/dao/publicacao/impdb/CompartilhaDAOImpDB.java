package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.edu.model.dao.GrupoDaoImpl;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
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
		String query = "INSERT INTO compartilha (usuarioid,publicacaoid,grupoid) "
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
					+ " WHERE (usuarioid = ?) AND "
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
	public int quant() throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM compartilha";
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar quantidade");
		}
		return 0;
	}

	@Override
	public int quant(Grupo grupo) throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM compartilha "
					+ "WHERE grupoid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, grupo.getId());
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar quantidade");
		}
		return 0;
	}

	@Override
	public int quant(Usuario usuario) throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM compartilha "
					+ "WHERE usuarioid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, usuario.getId());
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar quantidade");
		}
		return 0;
	}

	@Override
	public int quant(Publicacao publicacao) throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM compartilha "
					+ "WHERE publicacaoid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, publicacao.getId());
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar quantidade");
		}
		return 0;
	}

	@Override
	public List<Compartilha> lista() throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();
		UsuarioDaoImpl usuarioDAO = new UsuarioDaoImpl();
		PublicacaoDAOImpDB publicacaoDAO = new PublicacaoDAOImpDB();
		GrupoDaoImpl grupoDAO = new GrupoDaoImpl();
		try {
			String query = "SELECT * FROM compartilha "
					+ " ORDER BY datahora DESC ";
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(new Compartilha(
						rs.getTimestamp("datahora").toLocalDateTime(), 
						usuarioDAO.buscarPorId(rs.getInt("usuarioid")), 
						publicacaoDAO.buscar(rs.getInt("publicacaoid")), 
						grupoDAO.busca(rs.getInt("grupoid"))));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public List<Compartilha> lista(Grupo grupo) throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();
		UsuarioDaoImpl usuarioDAO = new UsuarioDaoImpl();
		PublicacaoDAOImpDB publicacaoDAO = new PublicacaoDAOImpDB();
		GrupoDaoImpl grupoDAO = new GrupoDaoImpl();
		try {
			String query = "SELECT * FROM compartilha "
					+ " WHERE grupoid = ? "
					+ " ORDER BY datahora DESC ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, grupo.getId());
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(new Compartilha(
						rs.getTimestamp("datahora").toLocalDateTime(), 
						usuarioDAO.buscarPorId(rs.getInt("usuarioid")), 
						publicacaoDAO.buscar(rs.getInt("publicacaoid")), 
						grupoDAO.busca(rs.getInt("grupoid"))));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public List<Compartilha> lista(Usuario usuario) throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();
		UsuarioDaoImpl usuarioDAO = new UsuarioDaoImpl();
		PublicacaoDAOImpDB publicacaoDAO = new PublicacaoDAOImpDB();
		GrupoDaoImpl grupoDAO = new GrupoDaoImpl();
		try {
			String query = "SELECT * FROM compartilha "
					+ " WHERE usuarioid = ? "
					+ " ORDER BY datahora DESC ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, usuario.getId());
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(new Compartilha(
						rs.getTimestamp("datahora").toLocalDateTime(), 
						usuarioDAO.buscarPorId(rs.getInt("usuarioid")), 
						publicacaoDAO.buscar(rs.getInt("publicacaoid")), 
						grupoDAO.busca(rs.getInt("grupoid"))));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public List<Compartilha> lista(Publicacao publicacao) throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();
		UsuarioDaoImpl usuarioDAO = new UsuarioDaoImpl();
		PublicacaoDAOImpDB publicacaoDAO = new PublicacaoDAOImpDB();
		GrupoDaoImpl grupoDAO = new GrupoDaoImpl();
		try {
			String query = "SELECT * FROM compartilha "
					+ " WHERE publicacaoid = ? "
					+ " ORDER BY datahora DESC ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, publicacao.getId());
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(new Compartilha(
						rs.getTimestamp("datahora").toLocalDateTime(), 
						usuarioDAO.buscarPorId(rs.getInt("usuarioid")), 
						publicacaoDAO.buscar(rs.getInt("publicacaoid")), 
						grupoDAO.busca(rs.getInt("grupoid"))));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public List<Compartilha> lista(int inicio, int quant) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compartilha> lista(Grupo grupo, int inicio, int quant) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compartilha> lista(Usuario usuario, int inicio, int quant) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compartilha> lista(Publicacao publicacao, int inicio, int quant) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
