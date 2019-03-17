package com.ifpb.edu.model.domain.publicacao;

import java.time.LocalDateTime;

import com.ifpb.edu.model.domain.Usuario;

public class Foto extends Publicacao {

	private String arquivo;

	public Foto() {
		super();
	}

	public Foto(int id, Boolean ativo, String conteudo, LocalDateTime datahora, Usuario usuario, int relevancia,
			String arquivo) {
		super(id, ativo, conteudo, datahora, usuario, relevancia);
		this.arquivo = arquivo;
	}

	public Foto(String conteudo, Usuario usuario, int relevancia, String arquivo) {
		super(conteudo, usuario, relevancia);
		this.arquivo = arquivo;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public String toString() {
		return "Foto [arquivo=" + arquivo + ", getRelevancia()=" + getRelevancia() + ", toString()=" + super.toString()
				+ ", getId()=" + getId() + ", getAtivo()=" + getAtivo() + ", getConteudo()=" + getConteudo()
				+ ", getDatahora()=" + getDatahora() + ", getUsuario()=" + getUsuario() + "]";
	}

}
