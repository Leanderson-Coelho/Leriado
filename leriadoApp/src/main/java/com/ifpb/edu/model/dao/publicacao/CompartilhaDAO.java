package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Compartilha;
import com.ifpb.edu.model.domain.publicacao.Publicacao;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface CompartilhaDAO {
	
	void cria(Compartilha compartilha) throws DataAccessException;
	void exclui(Compartilha compartilha) throws DataAccessException;
	int quant()throws DataAccessException;
	int quant(Grupo grupo) throws DataAccessException;
	int quant(Usuario usuario) throws DataAccessException;
	int quant(Publicacao publicacao)throws DataAccessException;
	List<Compartilha> lista() throws DataAccessException;
	List<Compartilha> lista(Grupo grupo) throws DataAccessException;
	List<Compartilha> lista(Usuario usuario) throws DataAccessException;
	List<Compartilha> lista(Publicacao publicacao) throws DataAccessException;
	List<Compartilha> lista(int inicio, int quant) throws DataAccessException;
	List<Compartilha> lista(Grupo grupo, int inicio, int quant) throws DataAccessException;
	List<Compartilha> lista(Usuario usuario, int inicio, int quant) throws DataAccessException;
	List<Compartilha> lista(Publicacao publicacao, int inicio, int quant) throws DataAccessException;
	int quantFeed(Usuario usuario) throws DataAccessException;
	List<Compartilha> feed(Usuario usuario, int inicio, int quant)throws DataAccessException;
}
