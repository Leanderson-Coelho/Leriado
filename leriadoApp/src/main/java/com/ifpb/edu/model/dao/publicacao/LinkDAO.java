package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Link;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface LinkDAO {
	void cria(Link link) throws DataAccessException;
	void exclui(Link link) throws DataAccessException;
	int quant()throws DataAccessException;
	List<Link> lista() throws DataAccessException;
}
