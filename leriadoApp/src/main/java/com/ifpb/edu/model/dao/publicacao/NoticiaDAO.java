package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Noticia;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface NoticiaDAO {
	void cria(Noticia noticia) throws DataAccessException;
	void exclui(Noticia noticia) throws DataAccessException;
	Noticia buscar(int id) throws DataAccessException;
	void buscar(Noticia noticia) throws DataAccessException;
	void buscar(int id, Noticia noticia) throws DataAccessException;
	int quant()throws DataAccessException;
	List<Noticia> lista() throws DataAccessException;
	List<Noticia> lista(int inicio, int quant) throws DataAccessException;
}
