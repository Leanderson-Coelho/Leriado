package com.ifpb.edu.model.domain.publicacao;

import java.time.LocalDateTime;

import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.Usuario;

public class Texto {

	private int id = 0;
	private Boolean ativo = true;
	private String conteudo;
	private LocalDateTime datahora = LocalDateTime.now();
	private Usuario usuario;
	private TipoTexto tipoTexto;

	public Texto() {
	}

	public Texto(int id, Boolean ativo, String conteudo, LocalDateTime datahora, Usuario usuario, TipoTexto tipoTexto) {
		this.id = id;
		this.ativo = ativo;
		this.conteudo = conteudo;
		this.datahora = datahora;
		this.usuario = usuario;
		this.tipoTexto = tipoTexto;		
	}

	public Texto(String conteudo, Usuario usuario) {
		super();
		this.conteudo = conteudo;
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

	public TipoTexto getTipoTexto() {
		return tipoTexto;
	}

	public void setTipoTexto(TipoTexto tipoTexto) {
		this.tipoTexto = tipoTexto;
	}

	@Override
	public String toString() {
		return "Texto [id=" + id + ", ativo=" + ativo + ", conteudo=" + conteudo + ", datahora=" + datahora
				+ ", usuario=" + usuario + ", tipoTexto=" + tipoTexto + "]";
	}

	
}
