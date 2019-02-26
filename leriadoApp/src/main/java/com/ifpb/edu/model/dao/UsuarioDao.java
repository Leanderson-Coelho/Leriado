package com.ifpb.edu.model.dao;

import java.sql.SQLException;

import com.ifpb.edu.model.domain.Usuario;

public interface UsuarioDao {
	
	void criar(Usuario usuario) throws SQLException;
	void atualizar(Usuario usuarioNovo, Integer idUsuario);
	void remover(Integer idUsuario);
	Usuario buscarPorId(Integer id);
	Usuario buscarPorEmail(String email) throws SQLException;
	
}
