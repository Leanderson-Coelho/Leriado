package com.ifpb.edu.model.dao.publicacao;

import java.util.List;
import java.util.Optional;

import com.ifpb.edu.model.domain.publicacao.Comentario;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface ComentarioDAO {

	void cria(Comentario comentario) throws DataAccessException;
	void edita(Comentario comentario) throws DataAccessException;
	void exclui(Comentario comentario) throws DataAccessException;	
	Optional<Comentario> buscar(int id) throws DataAccessException;
	int quant(Texto texto) throws DataAccessException;
	List<Comentario> lista(Texto texto) throws DataAccessException;
	List<Comentario>lista(Texto texto, int inicio,int quant) throws DataAccessException;
}
