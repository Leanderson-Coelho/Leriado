package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Curte;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface CurteDAO {
	
	void criaCurte(Curte curte) throws DataAccessException;
	void editaCurte(Curte curte) throws DataAccessException;
	void excluiCurte(Curte curte) throws DataAccessException;
	int quantCurtidas(Texto texto) throws DataAccessException;
	List<Curte> listaCurte(Texto texto) throws DataAccessException;
}
