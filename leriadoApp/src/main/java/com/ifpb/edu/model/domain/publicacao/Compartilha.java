package com.ifpb.edu.model.domain.publicacao;

import java.time.LocalDateTime;

import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;

public class Compartilha {
	private LocalDateTime dataHora;
	private Usuario usuario;
	private Publicacao publicacao;
	private Grupo grupo;
	
	public Compartilha() {
	}

	public Compartilha(LocalDateTime dataHora, Usuario usuario, Publicacao publicacao, Grupo grupo) {
		this.dataHora = dataHora;
		this.usuario = usuario;
		this.publicacao = publicacao;
		this.grupo = grupo;
	}	

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
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
