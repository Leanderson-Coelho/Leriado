package com.ifpb.edu.model.dao.publicacao.impdb;

import java.util.ArrayList;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.FeedPublicacaoDAO;
import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Compartilha;
import com.ifpb.edu.model.domain.publicacao.FeedPublicacao;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class FeedPublicacaoDAOImpDB implements FeedPublicacaoDAO {

	private Usuario self;
	private CompartilhaDAOImpDB compartilhaDAO;

	private List<FeedPublicacao> criaLista(List<Compartilha> compartilhados) throws DataAccessException {
		List<FeedPublicacao> lista = new ArrayList<FeedPublicacao>();
		CurteDAOImpDB curteDAO = new CurteDAOImpDB();
		FeedComentarioDAOImpDB feedComDAO = new FeedComentarioDAOImpDB(self);
		for (Compartilha compartilha : compartilhados) {
			lista.add(new FeedPublicacao(
					self, 
					compartilha,
					compartilha.getUsuario().getId() == compartilha.getPublicacao().getUsuario().getId(),
					curteDAO.existe(compartilha.getPublicacao().getId(), self.getId()),
					curteDAO.quant(compartilha.getPublicacao()),
					feedComDAO.quantComentarios(compartilha.getPublicacao(), true),
					compartilhaDAO.quant(compartilha.getPublicacao()), 
					null));

		}
		return lista;
	}

	public FeedPublicacaoDAOImpDB(Usuario self) {
		this.self = self;
		compartilhaDAO = new CompartilhaDAOImpDB();
	}

	
	
	@Override
	public int quantFeed() throws DataAccessException {
		return compartilhaDAO.quantFeed(self);
	}

	@Override
	public int quantFeed(Usuario usuario) throws DataAccessException {
		return compartilhaDAO.quant(usuario);
	}

	@Override
	public int quantFeed(Grupo grupo) throws DataAccessException {
		return compartilhaDAO.quant(grupo);
	}

	@Override
	public List<FeedPublicacao> listaFeed(int inicio, int quant) throws DataAccessException {

		return criaLista(compartilhaDAO.feed(self, inicio, quant));
	}

	@Override
	public List<FeedPublicacao> listaUsuario(Usuario usuario, int inicio, int quant) throws DataAccessException {
		return criaLista(compartilhaDAO.lista(usuario, inicio, quant));
	}

	@Override
	public List<FeedPublicacao> listaGrupo(Grupo grupo, int inicio, int quant) throws DataAccessException {
		return criaLista(compartilhaDAO.lista(grupo, inicio, quant));
	}

	@Override
	public void carregarComentarios(List<FeedPublicacao> lista) throws DataAccessException {
		FeedComentarioDAOImpDB feedComDAO = new FeedComentarioDAOImpDB(self);
		for (FeedPublicacao feedPublicacao : lista) {
			feedPublicacao.setFeedComentarios(feedComDAO.buscar(feedPublicacao.getCompartilha().getPublicacao()));
		}
	}

	@Override
	public void carregarComentarios(List<FeedPublicacao> lista, int quant) throws DataAccessException {
		FeedComentarioDAOImpDB feedComDAO = new FeedComentarioDAOImpDB(self);
		for (FeedPublicacao feedPublicacao : lista) {
			feedPublicacao.setFeedComentarios(feedComDAO.buscar(feedPublicacao.getCompartilha().getPublicacao(),0,quant));
		}
	}

	@Override
	public void carregarComentarios(FeedPublicacao feedPublicacao, int inicio, int quant) throws DataAccessException {
		FeedComentarioDAOImpDB feedComDAO = new FeedComentarioDAOImpDB(self);
		feedPublicacao.setFeedComentarios(feedComDAO.buscar(feedPublicacao.getCompartilha().getPublicacao(),inicio,quant));
	}
	

}
