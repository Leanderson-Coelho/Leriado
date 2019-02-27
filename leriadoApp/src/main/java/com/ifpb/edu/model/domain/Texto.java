package com.ifpb.edu.model.domain;

import java.time.LocalDateTime;

public class Texto {
	
	private int id;
	private Boolean ativo;
	private String conteudo;
	private LocalDateTime datahora;
	private Usuario usuario;
	
	public Texto() {}

	public Texto(int id, Boolean ativo, String conteudo, LocalDateTime datahora, Usuario usuario) {		
		this.id = id;
		this.ativo = ativo;
		this.conteudo = conteudo;
		this.datahora = datahora;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public LocalDateTime getDatahora() {
		return datahora;
	}

	public void setDatahora(LocalDateTime datahora) {
		this.datahora = datahora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Texto [id=" + id + ", ativo=" + ativo + ", conteudo=" + conteudo + ", datahora=" + datahora
				+ ", usuario=" + usuario + "]";
	}
		
}
