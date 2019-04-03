package com.ifpb.edu.model.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface GrupoDao {
	public void criar(Grupo novoGrupo) throws SQLException;
	public void excluir(int idGrupo) throws SQLException;
	void adicionarUsuario(int idGrupo, int idUsuario) throws SQLException;
	void removerUsuario(int idGrupo, int idUsuario) throws SQLException;
	Grupo busca(int id)throws DataAccessException;
	public int buscaIdPorNome(String nome) throws DataAccessException;
	public List<String> buscarGruposUsuarioParticipa(int idUsuario) throws DataAccessException;
	public List<Grupo> admsGrupo(int idUsuario) throws DataAccessException;
	public void adicionarAdministrador(int idUsuario,int idGrupo) throws DataAccessException;
	public List<Grupo> listar();
	
}

