package com.ifpb.edu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	public void excluir(int idGrupo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarUsuario(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerUsuario(String email) {
		// TODO Auto-generated method stub
		
	}

}