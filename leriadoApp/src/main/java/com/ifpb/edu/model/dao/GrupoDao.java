package com.ifpb.edu.model.dao;

import java.sql.SQLException;

import com.ifpb.edu.model.domain.Grupo;

public interface GrupoDao {
	public void criar(Grupo novoGrupo) throws SQLException;
	public void excluir(int idGrupo);
	public void adicionarUsuario(String email);
	public void removerUsuario(String email);
}
