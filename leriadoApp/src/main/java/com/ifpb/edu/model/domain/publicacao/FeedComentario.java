package com.ifpb.edu.model.domain.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.Usuario;

public class FeedComentario {

	private Usuario self;
	private Comentario Comentario;
	private boolean curtido;
	private int quantCurtidas;
	private List<FeedComentario> respostas;

	public FeedComentario() {
	}

	public FeedComentario(Usuario self) {
		this.self = self;
	}

	public FeedComentario(Usuario self, com.ifpb.edu.model.domain.publicacao.Comentario comentario, boolean curtido,
			int quantCurtidas, List<FeedComentario> respostas) {
		super();
		this.self = self;
		Comentario = comentario;
		this.curtido = curtido;
		this.quantCurtidas = quantCurtidas;
		this.respostas = respostas;
	}

	public Usuario getSelf() {
		return self;
	}

	public void setSelf(Usuario self) {
		this.self = self;
	}

	public Comentario getComentario() {
		return Comentario;
	}

	public void setComentario(Comentario comentario) {
		Comentario = comentario;
	}

	public boolean isCurtido() {
		return curtido;
	}

	public void setCurtido(boolean curtido) {
		this.curtido = curtido;
	}

	public int getQuantCurtidas() {
		return quantCurtidas;
	}

	public void setQuantCurtidas(int quantCurtidas) {
		this.quantCurtidas = quantCurtidas;
	}

	public List<FeedComentario> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<FeedComentario> respostas) {
		this.respostas = respostas;
	}

	@Override
	public String toString() {
		return "FeedComentario [self=" + self + ", Comentario=" + Comentario + ", curtido=" + curtido
				+ ", quantCurtidas=" + quantCurtidas + ", respostas=" + respostas + "]";
	}

}
