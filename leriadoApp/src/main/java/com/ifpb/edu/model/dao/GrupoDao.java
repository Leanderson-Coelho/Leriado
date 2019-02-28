package com.ifpb.edu.model.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;

import com.ifpb.edu.model.domain.Grupo;

public interface GrupoDao {
	public void criar(Grupo novoGrupo) throws SQLException;
	public void excluir(int idGrupo) throws SQLException;
	void adicionarUsuario(int idGrupo, int idUsuario) throws SQLException;
	void removerUsuario(int idGrupo, int idUsuario) throws SQLException;
}
