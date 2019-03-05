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
	List<Publicacao> lista() throws DataAccessException;
	List<Publicacao> lista(Grupo grupo) throws DataAccessException;
	List<Publicacao> lista(Usuario usuario) throws DataAccessException;
}
