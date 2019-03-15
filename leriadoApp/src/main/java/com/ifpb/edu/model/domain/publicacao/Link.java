package com.ifpb.edu.model.domain.publicacao;

import java.time.LocalDateTime;

import com.ifpb.edu.model.domain.Usuario;

public class Link extends Publicacao {

	private String link;

	public Link() {
		super();
	}

	public Link(int id, Boolean ativo, String conteudo, LocalDateTime datahora, Usuario usuario, int curtidas,
			int quantComentarios, int relevancia, int compartilhamentos, String link) {
		super(id, ativo, conteudo, datahora, usuario, curtidas, quantComentarios, relevancia, compartilhamentos);
		this.link = link;
	}

	public Link(String conteudo, Usuario usuario, int relevancia, String link) {
		super(conteudo, usuario, relevancia);
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Link [link=" + link + ", getRelevancia()=" + getRelevancia() + ", toString()=" + super.toString()
				+ ", getId()=" + getId() + ", getAtivo()=" + getAtivo() + ", getConteudo()=" + getConteudo()
				+ ", getDatahora()=" + getDatahora() + ", getUsuario()=" + getUsuario() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
