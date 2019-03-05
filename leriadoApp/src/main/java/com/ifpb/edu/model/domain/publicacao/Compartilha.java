package com.ifpb.edu.model.domain.publicacao;

import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;

public class Compartilha {
	private Usuario usuario;
	private Publicacao publicacao;
	private Grupo grupo;
	
	public Compartilha() {
	}

	public Compartilha(Usuario usuario, Publicacao publicacao, Grupo grupo) {
		this.usuario = usuario;
		this.publicacao = publicacao;
		this.grupo = grupo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "Compartilha [usuario=" + usuario + ", publicacao=" + publicacao + ", grupo=" + grupo + "]";
	}
		
}
