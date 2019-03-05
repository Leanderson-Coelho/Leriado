
package com.ifpb.edu.model.domain;

import java.time.LocalDateTime;

public class Grupo {
	private int id;
	private boolean ativo;
	private LocalDateTime dataHora;
	private String nome;
	private String descricao;
	private String foto;
	
	public Grupo() {		
	}

	public Grupo(int id, boolean ativo, LocalDateTime dataHora, String nome, String descricao, String foto) {
		super();
		this.id = id;
		this.ativo = ativo;
		this.dataHora = dataHora;
		this.nome = nome;
		this.descricao = descricao;
		this.foto = foto;
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

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Grupo [ativo=" + ativo + ", dataHora=" + dataHora + ", nome=" + nome + ", descricao=" + descricao
				+ ", foto=" + foto + "]";
	}
	
}
