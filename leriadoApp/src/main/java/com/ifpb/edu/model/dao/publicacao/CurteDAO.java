package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Curte;
import com.ifpb.edu.model.domain.publicacao.Texto;

public interface CurteDAO {
	
	int criaCurte(Curte curte);
	void editaCurte(Curte curte);
	void excluiCurte(Curte curte);
	int quantCurtidas(Texto texto);
	List<Curte> listaCurte(Texto texto);
}
