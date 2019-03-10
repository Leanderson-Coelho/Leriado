package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Link;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface LinkDAO {
	void cria(Link link) throws DataAccessException;
	void exclui(Link link) throws DataAccessException;
	Link buscar(int id) throws DataAccessException;
	void buscar(Link link) throws DataAccessException;
	void buscar(int id, Link link) throws DataAccessException;
	int quant()throws DataAccessException;
	List<Link> lista() throws DataAccessException;
	List<Link> lista(int inicio, int quant) throws DataAccessException;
}
