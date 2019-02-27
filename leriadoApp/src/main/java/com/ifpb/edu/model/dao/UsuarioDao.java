package com.ifpb.edu.model.dao;

import java.sql.SQLException;

import com.ifpb.edu.model.domain.Usuario;

public interface UsuarioDao {
	
	void criar(Usuario usuario) throws SQLException;
	void atualizar(Usuario usuarioNovo, Integer idUsuario) throws SQLException;
	void remover(Integer idUsuario) throws SQLException;
	Usuario buscarPorId(Integer id) throws SQLException;
	Usuario buscarPorEmail(String email) throws SQLException;
	boolean login(String email, String senha) throws SQLException;
}
