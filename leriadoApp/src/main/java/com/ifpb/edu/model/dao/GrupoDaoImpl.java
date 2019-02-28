package com.ifpb.edu.model.dao;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.jdbc.ConnectionFactory;

public class GrupoDaoImpl implements GrupoDao{
	private Connection connection;
	
	public GrupoDaoImpl() {
		connection = ConnectionFactory.getInstance().getConnection();
	}
	
	@Override
	public void criar(Grupo novoGrupo) throws SQLException {
		String sql = new String("INSERT INTO grupo (ativo,nome,descricao,foto) VALUES (?,?,?,?)");
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setBoolean(1, novoGrupo.isAtivo());
		statement.setString(2, novoGrupo.getNome());
		statement.setString(3, novoGrupo.getDescricao());
		statement.setString(4, novoGrupo.getFoto());
		statement.execute();
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
	public void removerUsuario(String email) {
		// TODO Auto-generated method stub
		
	}

}
