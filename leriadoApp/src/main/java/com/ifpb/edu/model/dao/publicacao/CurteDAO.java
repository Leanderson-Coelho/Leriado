package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Curte;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface CurteDAO {
	
	void cria(Curte curte) throws DataAccessException;
	void cria(int textoId, int usuarioId) throws DataAccessException;
	void exclui(Curte curte) throws DataAccessException;
	void exclui(int textoId, int usuarioId) throws DataAccessException;
	int quant(Texto texto) throws DataAccessException;
	boolean existe(int textoId, int usuarioId) throws DataAccessException;
	List<Curte> lista(Texto texto) throws DataAccessException;
}
