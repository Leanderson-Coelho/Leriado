package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Texto;

public interface TextoDAO {	
	int criaTexto(Texto texto);
	void editaTexto(Texto texto);
	void excluiTexto(Texto texto);
	TipoTexto tipoTexto(Texto texto);
	List<Texto> listaTexto();
	List<Texto>listaTexto(int inicio,int quant);
}
