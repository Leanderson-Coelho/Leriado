package com.ifpb.edu.model.dao.publicacao.impdb;

import java.util.ArrayList;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.FeedComentarioDAO;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Comentario;
import com.ifpb.edu.model.domain.publicacao.FeedComentario;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class FeedComentarioDAOImpDB implements FeedComentarioDAO {

	private ComentarioDAOImpDB comentarioDAO;
	private Usuario self;

	private List<FeedComentario> criaLista(List<Comentario> comentarios) throws DataAccessException {
		List<FeedComentario> lista = new ArrayList<FeedComentario>();
		CurteDAOImpDB curteDAO = new CurteDAOImpDB();
		for (Comentario comentario : comentarios) {
			lista.add(new FeedComentario(self, comentario, curteDAO.existe(comentario.getId(), self.getId()),
					curteDAO.quant(comentario), null));

		}
		return lista;
	}

	public FeedComentarioDAOImpDB(Usuario self) {
		this.self = self;
		comentarioDAO = new ComentarioDAOImpDB();
	}

	@Override
	public int quantComentarios(Texto texto, boolean recusivo) throws DataAccessException {
		int quantidade = comentarioDAO.quant(texto);
		if (recusivo) {
			List<Comentario> lista = comentarioDAO.lista(texto);
			for (Comentario comentario : lista) {
				quantidade += quantComentarios(comentario, recusivo);
			}
		}
		return quantidade;
	}

	@Override
	public List<FeedComentario> buscar(Texto texto) throws DataAccessException {
		return criaLista(comentarioDAO.lista(texto));
	}

	@Override
	public List<FeedComentario> buscar(Texto texto, int inicio, int quant) throws DataAccessException {
		return criaLista(comentarioDAO.lista(texto, inicio, quant));
	}

}
