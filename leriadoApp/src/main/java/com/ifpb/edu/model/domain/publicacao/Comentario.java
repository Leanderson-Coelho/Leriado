package com.ifpb.edu.model.domain.publicacao;

import java.time.LocalDateTime;

import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.Usuario;

public class Comentario extends Texto {

	private Texto responde;

	public Comentario() {
		super();
	}

	public Comentario(int id, Boolean ativo, String conteudo, LocalDateTime datahora, Usuario usuario, Texto responde) {
		super(id, ativo, conteudo, datahora, usuario, TipoTexto.COMENTARIO);
		this.responde = responde;
	}

	public Comentario(String conteudo, Usuario usuario, Texto responde) {
		super(conteudo, usuario);
		this.responde = responde;
	}

	public Texto getResponde() {
		return responde;
	}

	public void setResponde(Texto responde) {
		this.responde = responde;
	}

	@Override
	public String toString() {
		return "Comentario [responde=" + responde + ", getId()=" + getId() + ", getAtivo()=" + getAtivo()
				+ ", getConteudo()=" + getConteudo() + ", getDatahora()=" + getDatahora() + ", getUsuario()="
				+ getUsuario() + "]";
	}

}
