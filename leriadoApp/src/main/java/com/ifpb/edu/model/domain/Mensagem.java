package com.ifpb.edu.model.domain;

import java.time.LocalDate;

import org.bson.codecs.pojo.annotations.BsonId;

public class Mensagem {
	private String idRemetente;
	private String idDestinatario;
	private LocalDate dataEnvio;
	private String conteudo;
	
	public Mensagem() {}
	public Mensagem(String idRemetente, String idDestinatario, LocalDate dataEnvio,
			String conteudo) {
		this.idRemetente = idRemetente;
		this.idDestinatario = idDestinatario;
		this.dataEnvio = dataEnvio;
		this.conteudo = conteudo;
	}
	public String getIdRemetente() {
		return idRemetente;
	}
	public void setIdRemetente(String idRemetente) {
		this.idRemetente = idRemetente;
	}
	public String getIdDestinatario() {
		return idDestinatario;
	}
	public void setIdDestinatario(String idDestinatario) {
		this.idDestinatario = idDestinatario;
	}
	public LocalDate getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	@Override
	public String toString() {
		return "Mensagem [idRemetente=" + idRemetente + ", idDestinatario="
				+ idDestinatario + ", dataEnvio=" + dataEnvio + ", conteudo=" + conteudo + "]";
	}
	
	
}
