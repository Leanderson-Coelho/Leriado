package com.ifpb.edu.model.domain;

import java.time.LocalDateTime;

public class Texto {
	
	private int id;
	private boolean ativo;
	private String conteudo;
	private LocalDateTime datahora;
	private Integer idUsuario;

	public Texto() {}

	public Texto(int id, boolean ativo, String conteudo, LocalDateTime datahora, Integer idUsuario) {
		super();
		this.id = id;
		this.ativo = ativo;
		this.conteudo = conteudo;
		this.datahora = datahora;
		this.idUsuario = idUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
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

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Texto [id=" + id + ", ativo=" + ativo + ", conteudo=" + conteudo + ", datahora=" + datahora
				+ ", idUsuario=" + idUsuario + "]";
	}	
	

}
