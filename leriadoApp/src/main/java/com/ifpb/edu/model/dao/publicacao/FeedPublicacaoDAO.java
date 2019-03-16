package com.ifpb.edu.model.dao.publicacao;

import java.util.List;

import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.FeedPublicacao;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface FeedPublicacaoDAO {
	
	int quantFeed() throws DataAccessException;
	
	int quantFeed(Usuario usuario) throws DataAccessException;

	int quantFeed(Grupo grupo) throws DataAccessException;

	List<FeedPublicacao> listaFeed(int inicio, int quant) throws DataAccessException;

	List<FeedPublicacao> listaUsuario(Usuario usuario, int inicio, int quant) throws DataAccessException;

	List<FeedPublicacao> listaGrupo(Grupo grupo, int inicio, int quant) throws DataAccessException;

	void carregarComentarios(List<FeedPublicacao> lista) throws DataAccessException;

	void carregarComentarios(List<FeedPublicacao> lista, int quant) throws DataAccessException;

	void carregarComentarios(FeedPublicacao feedPublicacao, int inicio, int quant) throws DataAccessException;
}
