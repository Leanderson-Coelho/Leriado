package com.ifpb.edu.model.domain.publicacao;

import java.time.LocalDateTime;
import java.util.List;

import com.ifpb.edu.model.domain.Usuario;

public class Noticia extends Publicacao{
	
	private String titulo;
	private List<Foto> fotos;

	public Noticia() {
		super();
	}

	public Noticia(int id, Boolean ativo, String titulo, String conteudo, LocalDateTime datahora, Usuario usuario, int relevancia, int curtidas, List<Foto> fotos) {
		super(id, ativo, conteudo, datahora, usuario, relevancia, curtidas);
		this.titulo = titulo;
		this.fotos = fotos;
	}

	public Noticia(String titulo, String conteudo, Usuario usuario, int relevancia, List<Foto> fotos) {
		super(conteudo, usuario, relevancia);
		this.titulo = titulo;
		this.fotos = fotos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	@Override
	public String toString() {
		return "Noticia [titulo=" + titulo + ", fotos=" + fotos + ", getRelevancia()=" + getRelevancia()
				+ ", toString()=" + super.toString() + ", getId()=" + getId() + ", getAtivo()=" + getAtivo()
				+ ", getConteudo()=" + getConteudo() + ", getDatahora()=" + getDatahora() + ", getUsuario()="
				+ getUsuario() + ", getTipoTexto()=" + getTipoTexto() + "]";
	}

	
	
	
}
