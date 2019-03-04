package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Marca;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface MarcaDAO {
	
	void cria(Marca marca) throws DataAccessException;
	void edita(Marca marca) throws DataAccessException;
	void exclui(Marca marca) throws DataAccessException;
	boolean existe(int textoId, int usuarioId) throws DataAccessException;
	List<Marca> listaMarca(Texto texto) throws DataAccessException;
}
