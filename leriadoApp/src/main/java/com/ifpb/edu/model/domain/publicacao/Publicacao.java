package com.ifpb.edu.model.domain.publicacao;

import java.time.LocalDateTime;

import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.Usuario;

public class Publicacao extends Texto{
	
	private int relevancia = 1;

	public Publicacao() {
		super();
	}

	public Publicacao(int id, Boolean ativo, String conteudo, LocalDateTime datahora, Usuario usuario, int relevancia, int curtidas) {
		super(id, ativo, conteudo, datahora, usuario,TipoTexto.PUBLICACAO,curtidas);
		this.relevancia = relevancia;
	}

	public Publicacao(String conteudo, Usuario usuario, int relevancia) {
		super(conteudo, usuario);
		this.relevancia = relevancia;
	}

	public int getRelevancia() {
		return relevancia;
	}

	public void setRelevancia(int relevancia) {
		this.relevancia = relevancia;
	}

	@Override
	public String toString() {
		return "Publicacao [relevancia=" + relevancia + ", getRelevancia()=" + getRelevancia() + ", getId()=" + getId()
				+ ", getAtivo()=" + getAtivo() + ", getConteudo()=" + getConteudo() + ", getDatahora()=" + getDatahora()
				+ ", getUsuario()=" + getUsuario() + ", toString()=" + super.toString() + "]";
	}
		
}
