<<<<<<< HEAD
package com.ifpb.edu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class GrupoDaoImpl implements GrupoDao{
	private Connection connection;
	
	public GrupoDaoImpl() {
		connection = ConnectionFactory.getInstance().getConnection();
	}
	
	@Override
	public void criar(Grupo novoGrupo) throws SQLException {
		String sql = "INSERT INTO grupo (ativo,nome,descricao,foto)"
				+ " VALUES (?,?,?,?)"
				+ " RETURNING id ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setBoolean(1, novoGrupo.isAtivo());
		statement.setString(2, novoGrupo.getNome());
		statement.setString(3, novoGrupo.getDescricao());
		statement.setString(4, novoGrupo.getFoto());
		ResultSet rs = statement.executeQuery();
		novoGrupo.setId(rs.getInt(1));
	}

	@Override
	public void excluir(int idGrupo) throws SQLException {
		String query = "update grupo set ativo=false where id=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idGrupo);
		statement.executeQuery();
	}

	@Override
	public void adicionarUsuario(int idGrupo,int idUsuario) throws SQLException {
		String sql = new String("INSERT INTO participagrupo (usuarioid,grupoid) VALUES (?,?)");
		PreparedStatement statement = connection.prepareStatement(sql); 		
		statement.setInt(1, idUsuario);
		statement.setInt(2,idGrupo);
		statement.execute();
	}

	@Override
	public void removerUsuario(int idGrupo,int idUsuario) throws SQLException {
		String sql = new String("DELETE FROM participagrupo WHERE usuarioid = ? and grupoid = ?");
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, idUsuario);
		statement.setInt(2, idGrupo);
		statement.execute();
	}

	@Override
	public Grupo busca(int id) throws DataAccessException {
		try {
			String query = "SELECT * FROM grupo "
					+ "WHERE id = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return new Grupo(
						rs.getInt("id"), 
						rs.getBoolean("ativo"),
						rs.getTimestamp("datahora").toLocalDateTime(),
						rs.getString("nome"),
						rs.getString("descricao"),
						rs.getString("foto"));
			}
		}catch (Exception e) {
			throw new DataAccessException("Fala ao buscar grupo");
		}
		return null;
	}
	
	public int buscaIdPorNome(String nome) throws DataAccessException {
		try {
			String query = "SELECT id FROM grupo "
					+ "WHERE nome = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setString(1, nome);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		}catch (Exception e) {
			throw new DataAccessException("Fala ao buscar grupo");
		}
		return -1;
	}

	@Override
	public List<String> buscarGruposUsuarioParticipa(int idUsuario) throws DataAccessException {
		List<String> gruposUsuarioParticipa = new ArrayList<>();
		try {
			String query = "SELECT grupoid FROM participagrupo "
					+ "WHERE usuarioid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, idUsuario);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				gruposUsuarioParticipa.add(busca(rs.getInt(1)).getNome());
				System.out.println(".");
			}
		}catch (Exception e) {
			throw new DataAccessException("Fala ao buscar grupo");
		}
		return gruposUsuarioParticipa;
	}
}
=======
package com.ifpb.edu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class GrupoDaoImpl implements GrupoDao{
	private Connection connection;
	
	public GrupoDaoImpl() {
		connection = ConnectionFactory.getInstance().getConnection();
	}
	
	@Override
	public void criar(Grupo novoGrupo) throws SQLException {
		String sql = "INSERT INTO grupo (ativo,nome,descricao,foto)"
				+ " VALUES (?,?,?,?)"
				+ " RETURNING id ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setBoolean(1, novoGrupo.isAtivo());
		statement.setString(2, novoGrupo.getNome());
		statement.setString(3, novoGrupo.getDescricao());
		statement.setString(4, novoGrupo.getFoto());
		ResultSet rs = statement.executeQuery();
		novoGrupo.setId(rs.getInt(1));
	}

	@Override
	public void excluir(int idGrupo) throws SQLException {
		String query = "update grupo set ativo=false where id=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idGrupo);
		statement.executeQuery();
	}

	@Override
	public void adicionarUsuario(int idGrupo,int idUsuario) throws SQLException {
		String sql = new String("INSERT INTO participagrupo (usuarioid,grupoid) VALUES (?,?)");
		PreparedStatement statement = connection.prepareStatement(sql); 		
		statement.setInt(1, idUsuario);
		statement.setInt(2,idGrupo);
		statement.execute();
	}

	@Override
	public void removerUsuario(int idGrupo,int idUsuario) throws SQLException {
		String sql = new String("DELETE FROM participagrupo WHERE usuarioid = ? and grupoid = ?");
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, idUsuario);
		statement.setInt(2, idGrupo);
		statement.execute();
	}

	@Override
	public Grupo busca(int id) throws DataAccessException {
		try {
			String query = "SELECT * FROM grupo "
					+ "WHERE id = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return new Grupo(
						rs.getInt("id"), 
						rs.getBoolean("ativo"),
						rs.getTimestamp("datahora").toLocalDateTime(),
						rs.getString("nome"),
						rs.getString("descricao"),
						rs.getString("foto"));
			}
		}catch (Exception e) {
			throw new DataAccessException("Fala ao buscar grupo");
		}
		return null;
	}
	
	public int buscaIdPorNome(String nome) throws DataAccessException {
		try {
			String query = "SELECT id FROM grupo "
					+ "WHERE nome = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setString(1, nome);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		}catch (Exception e) {
			throw new DataAccessException("Fala ao buscar grupo");
		}
		return -1;
	}

	@Override
	public List<Grupo> buscarGruposUsuarioParticipa(int idUsuario) throws DataAccessException {
		List<Grupo> gruposUsuarioParticipa = new ArrayList<>();
		try {
			String query = "SELECT grupoid FROM participagrupo "
					+ "WHERE usuarioid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, idUsuario);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				gruposUsuarioParticipa.add(busca(rs.getInt(1)));
			}
		}catch (Exception e) {
			throw new DataAccessException("Fala ao buscar grupo");
		}
		return gruposUsuarioParticipa;
	}
}
>>>>>>> a56379c9e66c51fd430f08149504600927308ffb
