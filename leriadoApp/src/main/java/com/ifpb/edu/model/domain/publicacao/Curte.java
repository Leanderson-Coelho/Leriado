package com.ifpb.edu.model.domain.publicacao;

import java.time.LocalDateTime;

import com.ifpb.edu.model.domain.Usuario;

public class Curte {
	
	private Texto texto;
	private Usuario usuario;
	private LocalDateTime dataHora;	
	
	public Curte() {
	}

	public Curte(Texto texto, Usuario usuario, LocalDateTime dataHora) {
		this.texto = texto;
		this.usuario = usuario;
		this.dataHora = dataHora;
	}

	public Texto getTexto() {
		return texto;
	}

	public void setTexto(Texto texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	@Override
	public String toString() {
		return "Curte [texto=" + texto + ", usuario=" + usuario + ", dataHora=" + dataHora + "]";
	}
	
}
