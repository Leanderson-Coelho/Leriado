package com.ifpb.edu.model.dao;

import com.ifpb.edu.model.domain.Usuario;

public interface UsuarioDao {
	
	void criar(Usuario usuario);
	void atualizar(Usuario usuarioNovo, Integer idUsuario);
	void remover(Integer idUsuario);
	Usuario buscarPorId(Integer id);
	Usuario buscarPorEmail(String email);
	
}
