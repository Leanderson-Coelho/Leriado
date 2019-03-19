package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.FeedComentario;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface FeedComentarioDAO {
	int quantComentarios(Texto texto, boolean recusivo) throws DataAccessException;

	List<FeedComentario> buscar(Texto texto) throws DataAccessException;

	List<FeedComentario> buscar(Texto texto, int inicio, int quant) throws DataAccessException;

}
