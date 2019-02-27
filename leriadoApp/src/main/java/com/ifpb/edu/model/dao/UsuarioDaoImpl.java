package com.ifpb.edu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.jdbc.ConnectionFactory;

public class UsuarioDaoImpl implements UsuarioDao{
	Connection connection;
	
	public UsuarioDaoImpl() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	public void criar(Usuario usuario) throws SQLException {
		
		String sql = "insert into usuario(email, senha, nome, sobrenome, sexo, datanasc, acesso, telefone, rua, cidade, estado, numero, cep)"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		statement.execute();
	}

	public void atualizar(Usuario usuarioNovo, Integer idUsuario) throws SQLException{
		
		
	}

	public void remover(Integer idUsuario) throws SQLException{
		String query = "delete from usuario where id=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idUsuario);
		statement.executeUpdate();
	}

	public Usuario buscarPorId(Integer id) throws SQLException {
		String query = new String("SELECT * FROM usuario WHERE id = ?");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			Usuario usuario = new Usuario();
			usuario.setEmail(result.getString(1));
			usuario.setSenha(result.getString(2));
			usuario.setNome(result.getString(3));
			usuario.setSobrenome(result.getString(4));
			usuario.setSexo(result.getString(5));
			usuario.setDatanasc(result.getObject(6,LocalDate.class));
			usuario.setAcesso(result.getInt(7));
			usuario.setTelefone(result.getString(8));
			usuario.setRua(result.getString(9));
			usuario.setCidade(result.getString(10));
			usuario.setEstado(result.getString(11));
			usuario.setNumero(result.getString(12));
			usuario.setCep(result.getString(13));
			return usuario;
		}
		return null;
	}

	public Usuario buscarPorEmail(String email) throws SQLException {
		
		String sql = "select * from usuario where email=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			return new Usuario(result.getString("email"),
					result.getString("senha"),
					result.getString("nome"),
					result.getString("sobrenome"),
					result.getString("sexo"),
					result.getDate("datanasc").toLocalDate(),
					result.getInt("acesso"),
					result.getString("telefone"),
					result.getString("rua"),
					result.getString("cidade"),
					result.getString("estado"),
					result.getString("numero"),
					result.getString("cep"));
		}
		return null;
	}

	public boolean login(String email, String senha) throws SQLException {
		String query = "select id from usuario where email=? and senha=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, email);
		statement.setString(2, senha);
		ResultSet result = statement.executeQuery();
		return result.next();
	}
}
