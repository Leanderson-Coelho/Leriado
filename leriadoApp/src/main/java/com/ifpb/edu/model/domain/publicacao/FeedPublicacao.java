package com.ifpb.edu.model.domain.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.Usuario;

public class FeedPublicacao {

	private Usuario self;
	private Compartilha compartilha;
	private boolean seuConteudo;
	private boolean curtido;
	private int quantCurtidas;
	private int quantComentarios;
	private int quantCompartilhamentos;
	private List<FeedComentario> feedComentarios;

	public FeedPublicacao() {
	}

	public FeedPublicacao(Usuario self) {
		this.self = self;
	}

	public FeedPublicacao(Usuario self, Compartilha compartilha, boolean seuConteudo, boolean curtido, int quantCurtidas, int quantComentarios,
			int quantCompartilhamentos, List<FeedComentario> feedComentarios) {
		super();
		this.self = self;
		this.compartilha = compartilha;
		this.seuConteudo = seuConteudo;
		this.curtido = curtido;
		this.quantCurtidas = quantCurtidas;
		this.quantComentarios = quantComentarios;
		this.quantCompartilhamentos = quantCompartilhamentos;
		this.feedComentarios = feedComentarios;
	}

	public Usuario getSelf() {
		return self;
	}

	public void setSelf(Usuario self) {
		this.self = self;
	}
	
	public Compartilha getCompartilha() {
		return compartilha;
	}

	public void setCompartilha(Compartilha compartilha) {
		this.compartilha = compartilha;
	}

	public boolean isSeuConteudo() {
		return seuConteudo;
	}

	public void setSeuConteudo(boolean seuConteudo) {
		this.seuConteudo = seuConteudo;
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

	public int getQuantComentarios() {
		return quantComentarios;
	}

	public void setQuantComentarios(int quantComentarios) {
		this.quantComentarios = quantComentarios;
	}

	public int getQuantCompartilhamentos() {
		return quantCompartilhamentos;
	}

	public void setQuantCompartilhamentos(int quantCompartilhamentos) {
		this.quantCompartilhamentos = quantCompartilhamentos;
	}

	public List<FeedComentario> getFeedComentarios() {
		return feedComentarios;
	}

	public void setFeedComentarios(List<FeedComentario> feedComentarios) {
		this.feedComentarios = feedComentarios;
	}

	@Override
	public String toString() {
		return "FeedPublicacao [self=" + self + ", compartilha=" + compartilha + ", curtido=" + curtido
				+ ", quantCurtidas=" + quantCurtidas + ", quantComentarios=" + quantComentarios
				+ ", quantCompartilhamentos=" + quantCompartilhamentos + ", feedComentarios=" + feedComentarios
				+ ", getCompartilha()=" + getCompartilha() + ", isCurtido()=" + isCurtido() + ", getQuantCurtidas()="
				+ getQuantCurtidas() + ", getQuantComentarios()=" + getQuantComentarios()
				+ ", getQuantCompartilhamentos()=" + getQuantCompartilhamentos() + ", getFeedComentarios()="
				+ getFeedComentarios() + "]";
	}

}
