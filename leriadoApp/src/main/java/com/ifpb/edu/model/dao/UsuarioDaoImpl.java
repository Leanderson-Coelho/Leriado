package com.ifpb.edu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.jdbc.ConnectionFactory;

public class UsuarioDaoImpl implements UsuarioDao{
	Connection connection;
	
	public UsuarioDaoImpl() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void criar(Usuario usuario) {
		
		String sql = "insert into usuario(email, senha, nome, sobrenome, sexo, datanasc, acesso, telefone, rua, cidade, estado, numero, cep)"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void atualizar(Usuario usuarioNovo, Integer idUsuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Integer idUsuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
