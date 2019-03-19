package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Publicacao;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface PublicacaoDAO {
	void cria(Publicacao publicacao) throws DataAccessException;
	void edita(Publicacao publicacao) throws DataAccessException;
	void exclui(Publicacao publicacao) throws DataAccessException;
	Publicacao buscar(int id) throws DataAccessException;
	void buscar(Publicacao publicacao) throws DataAccessException;
	void buscar(int id, Publicacao publicacao) throws DataAccessException;
	int quant()throws DataAccessException;
	List<Publicacao> lista() throws DataAccessException;
	List<Publicacao> lista(int inicio, int quant) throws DataAccessException;
}
