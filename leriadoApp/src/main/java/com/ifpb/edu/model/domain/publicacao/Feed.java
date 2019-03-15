package com.ifpb.edu.model.domain.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.Usuario;

public class Feed {
	private Usuario usuario;
	private Publicacao publicacao;
	private int quantComentarios;
	private List<Comentario> comentarios;
	private Boolean curtido;
}
