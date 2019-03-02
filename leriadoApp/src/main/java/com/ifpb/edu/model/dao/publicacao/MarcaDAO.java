package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Marca;
import com.ifpb.edu.model.domain.publicacao.Texto;

public interface MarcaDAO {
	
	int criaMarca(Marca marca);
	void editaMarca(Marca marca);
	void excluiMarca(Marca marca);
	List<Marca> listaMarca(Texto texto);
}
