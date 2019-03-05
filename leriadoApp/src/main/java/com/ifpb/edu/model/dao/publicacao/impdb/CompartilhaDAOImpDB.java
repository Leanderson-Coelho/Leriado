package com.ifpb.edu.model.dao.publicacao.impdb;

import java.util.List;

import com.ifpb.edu.model.dao.publicacao.CompartilhaDAO;
import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Compartilha;
import com.ifpb.edu.model.domain.publicacao.Publicacao;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class CompartilhaDAOImpDB implements CompartilhaDAO {

	@Override
	public void cria(Compartilha compartilha) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void exclui(Compartilha compartilha) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Publicacao> lista() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publicacao> lista(Grupo grupo) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publicacao> lista(Usuario usuario) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
