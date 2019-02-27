package com.ifpb.edu.model.dao;

import com.ifpb.edu.model.domain.Grupo;

public interface GrupoDao {
	public void criar(Grupo novoGrupo);
	public void excluir(int idGrupo);
	public void adicionarUsuario(String email);
	public void removerUsuario(String email);
}
