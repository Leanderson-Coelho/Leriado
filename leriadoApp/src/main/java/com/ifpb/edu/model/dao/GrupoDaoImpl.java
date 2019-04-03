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
		rs.next();
		novoGrupo.setId(rs.getInt(1));
	}

	@Override
	public void excluir(int idGrupo) throws SQLException {
		String query = "update grupo set ativo=false where id=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idGrupo);
		statement.execute();
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
					+ "WHERE id = ? and ativo = true";
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
			String query = "SELECT g.id FROM participagrupo pg, grupo g "
					+ "WHERE pg.grupoid = g.id and pg.usuarioid = ? and g.ativo = true";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, idUsuario);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				gruposUsuarioParticipa.add(busca(rs.getInt(1)).getNome());
			}
		}catch (Exception e) {
			throw new DataAccessException("Fala ao buscar grupo");
		}
		return gruposUsuarioParticipa;
	}

	@Override
	public List<Grupo> admsGrupo(int idUsuario) throws DataAccessException {
		List<Grupo> IDgruposUsuarioadministra = new ArrayList<>();
		List<Grupo> gruposUsuarioAdministra;
		try {
			String query = "SELECT grupoid FROM admgrupo "
					+ "WHERE usuarioid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, idUsuario);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Grupo gp = new Grupo();
				gp.setId(rs.getInt(1));
				IDgruposUsuarioadministra.add(gp);
			}
			gruposUsuarioAdministra = new ArrayList<>();
			for(Grupo gp : IDgruposUsuarioadministra ) {
				gruposUsuarioAdministra.add(busca(gp.getId()));
			}
		}catch (Exception e) {
			throw new DataAccessException("Fala ao buscar grupo");
		}
		return gruposUsuarioAdministra;
	}

	@Override
	public void adicionarAdministrador(int idUsuario, int idGrupo) throws DataAccessException {
		try {
			String sql = new String("INSERT INTO admgrupo (usuarioid,grupoid) VALUES (?,?)");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idUsuario);
			statement.setInt(2,idGrupo);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Grupo> listar(){
		List<Grupo> grupos = new ArrayList<>();
		String sql = new String("SELECT * FROM grupo");
		try {
			PreparedStatement statemet = connection.prepareStatement(sql);
			ResultSet result = statemet.executeQuery();
			while(result.next()) {
				grupos.add(new Grupo(result.getInt(1),result.getBoolean(2),result.getTimestamp(3).toLocalDateTime(),result.getString(4),result.getString(5),result.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grupos;
	}
	
}
