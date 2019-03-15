package com.ifpb.edu.model.domain.publicacao;

import java.time.LocalDateTime;

import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.Usuario;

public class Publicacao extends Texto {

	private int relevancia = 1;
	private int compartilhamentos = 0;

	public Publicacao() {
	}

	public Publicacao(int id, Boolean ativo, String conteudo, LocalDateTime datahora, Usuario usuario, int curtidas,
			int quantComentarios, int relevancia, int compartilhamentos) {
		super(id, ativo, conteudo, datahora, usuario, TipoTexto.PUBLICACAO, curtidas, quantComentarios);
		this.relevancia = relevancia;
		this.compartilhamentos = compartilhamentos;
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

	public int getCompartilhamentos() {
		return compartilhamentos;
	}

	public void setCompartilhamentos(int compartilhamentos) {
		this.compartilhamentos = compartilhamentos;
	}

	@Override
	public String toString() {
		return "Publicacao [relevancia=" + relevancia + ", compartilhamentos=" + compartilhamentos + ", getId()="
				+ getId() + ", getAtivo()=" + getAtivo() + ", getConteudo()=" + getConteudo() + ", getDatahora()="
				+ getDatahora() + ", getUsuario()=" + getUsuario() + ", getTipoTexto()=" + getTipoTexto()
				+ ", getCurtidas()=" + getCurtidas() + ", getQuantComentarios()=" + getQuantComentarios() + "]";
	}

}
