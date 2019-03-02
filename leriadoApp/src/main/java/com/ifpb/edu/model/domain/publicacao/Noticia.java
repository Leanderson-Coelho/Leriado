package com.ifpb.edu.model.domain.publicacao;

import java.time.LocalDateTime;

import com.ifpb.edu.model.domain.Usuario;

public class Noticia extends Publicacao{
	
	private String titulo;

	public Noticia() {
		super();
	}

	public Noticia(int id, Boolean ativo, String titulo, String conteudo, LocalDateTime datahora, Usuario usuario, int relevancia) {
		super(id, ativo, conteudo, datahora, usuario, relevancia);
		this.titulo = titulo;
	}

	public Noticia(String titulo, String conteudo, Usuario usuario, int relevancia) {
		super(conteudo, usuario, relevancia);
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "Noticia [titulo=" + titulo + ", getRelevancia()=" + getRelevancia()
				+ ", toString()=" + super.toString() + ", getId()=" + getId() + ", getAtivo()=" + getAtivo()
				+ ", getConteudo()=" + getConteudo() + ", getDatahora()=" + getDatahora() + ", getUsuario()="
				+ getUsuario() + "]";
	}
	
	
}
