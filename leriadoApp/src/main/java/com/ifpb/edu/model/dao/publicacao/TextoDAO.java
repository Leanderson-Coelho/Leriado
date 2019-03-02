package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.publicacao.Texto;

public interface TextoDAO {	
	int cria(Texto texto);
	void edita(Texto texto);
	void exclui(Texto texto);
	TipoTexto tipo(Texto texto);
	List<Texto> lista();
	List<Texto>lista(int inicio,int quant);
}
