package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface TextoDAO {	
	int cria(Texto texto) throws DataAccessException;
	void edita(Texto texto) throws DataAccessException;
	void exclui(Texto texto) throws DataAccessException;
	TipoTexto tipo(Texto texto) throws DataAccessException;
	List<Texto> lista() throws DataAccessException;
	List<Texto>lista(int inicio,int quant) throws DataAccessException;
}
