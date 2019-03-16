package com.ifpb.edu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.jdbc.ConnectionFactory;

public class UsuarioDaoImpl implements UsuarioDao{
	private Connection connection;
	
	public UsuarioDaoImpl() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void criar(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuario(email, senha, nome, sobrenome, sexo, datanasc, acesso, telefone, rua, cidade, estado, numero, cep)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
				+ " RETURNING id";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getEmail());
		statement.setString(2, usuario.getSenha());
		statement.setString(3, usuario.getNome());
		statement.setString(4, usuario.getSobrenome());
		statement.setString(5, usuario.getSexo());
		statement.setDate(6, java.sql.Date.valueOf(usuario.getDatanasc()));
		statement.setInt(7, usuario.getAcesso());
		statement.setString(8, usuario.getTelefone());
		statement.setString(9, usuario.getRua());
		statement.setString(10, usuario.getCidade());
		statement.setString(11, usuario.getEstado());
		statement.setString(12, usuario.getNumero());
		statement.setString(13, usuario.getCep());
		ResultSet rs = statement.executeQuery();
		rs.next();
		usuario.setId(rs.getInt(1));
	}

	@Override
	public void atualizar(Usuario usuarioNovo, Integer idUsuario) throws SQLException{
		String query = new String("UPDATE usuario SET "
				+ "ativo=?, email=?,senha=?,nome=?,sobrenome=?,sexo=?,datanasc=?,acesso=?,telefone=?,rua=?,cidade=?,estado=?,numero=?,cep=?"
				+ " WHERE id = ?");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setBoolean(1, usuarioNovo.isAtivo());
		statement.setString(2, usuarioNovo.getEmail());
		statement.setString(3, usuarioNovo.getSenha());
		statement.setString(4, usuarioNovo.getNome());
		statement.setString(5, usuarioNovo.getSobrenome());
		statement.setString(6, usuarioNovo.getSexo());
		statement.setDate(7, java.sql.Date.valueOf(usuarioNovo.getDatanasc()));
		statement.setInt(8, usuarioNovo.getAcesso());
		statement.setString(9, usuarioNovo.getTelefone());
		statement.setString(10, usuarioNovo.getRua());
		statement.setString(11, usuarioNovo.getCidade());
		statement.setString(12, usuarioNovo.getEstado());
		statement.setString(13, usuarioNovo.getNumero());
		statement.setString(14, usuarioNovo.getCep());
		statement.setInt(15, idUsuario);
		statement.execute();
	}
	
	@Override
	public void remover(Integer idUsuario) throws SQLException{
		String query = "update usuario set ativo=FALSE where id=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idUsuario);
		statement.executeUpdate();
	}
	
	@Override
	public Usuario buscarPorId(Integer id) throws SQLException {
		String query = new String("SELECT * FROM usuario WHERE id = ?");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(result.getInt("id"));
			usuario.setAtivo(result.getBoolean("ativo"));
			usuario.setEmail(result.getString("email"));
			usuario.setSenha(result.getString("senha"));
			usuario.setNome(result.getString("nome"));
			usuario.setSobrenome(result.getString("sobrenome"));
			usuario.setSexo(result.getString("sexo"));
			usuario.setDatanasc(result.getDate("datanasc").toLocalDate());
			usuario.setAcesso(result.getInt("acesso"));
			usuario.setTelefone(result.getString("telefone"));
			usuario.setRua(result.getString("rua"));
			usuario.setCidade(result.getString("cidade"));
			usuario.setEstado(result.getString("estado"));
			usuario.setNumero(result.getString("numero"));
			usuario.setCep(result.getString("cep"));
			return usuario;
		}
		return null;
	}
	
	@Override
	public Usuario buscarPorEmail(String email) throws SQLException {
		String sql = "select * from usuario where email=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(result.getInt("id"));
			usuario.setAtivo(result.getBoolean("ativo"));
			usuario.setEmail(result.getString("email"));
			usuario.setSenha(result.getString("senha"));
			usuario.setNome(result.getString("nome"));
			usuario.setSobrenome(result.getString("sobrenome"));
			usuario.setSexo(result.getString("sexo"));
			usuario.setDatanasc(result.getDate("datanasc").toLocalDate());
			usuario.setAcesso(result.getInt("acesso"));
			usuario.setTelefone(result.getString("telefone"));
			usuario.setRua(result.getString("rua"));
			usuario.setCidade(result.getString("cidade"));
			usuario.setEstado(result.getString("estado"));
			usuario.setNumero(result.getString("numero"));
			usuario.setCep(result.getString("cep"));
			return usuario;
		}
		return null;
	}
	
	@Override
	public boolean login(String email, String senha) throws SQLException {
		String query = "select count(*) from usuario where email=? and senha=? and ativo=TRUE";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, email);
		statement.setString(2, senha);
		ResultSet result = statement.executeQuery();
		result.next();
		if(result.getInt(1) == 1) {
			return true;
		}
		return false;
	}
}
