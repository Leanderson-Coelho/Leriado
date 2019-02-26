package com.ifpb.edu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.jdbc.ConnectionFactory;

public class UsuarioDaoImpl implements UsuarioDao{
	Connection connection;
	
	public UsuarioDaoImpl() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
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

	@Override
	public void atualizar(Usuario usuarioNovo, Integer idUsuario) {
		
		
	}

	@Override
	public void remover(Integer idUsuario) {
		
		
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		
		return null;
	}

	@Override
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
}
