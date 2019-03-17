package com.ifpb.edu.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.GrupoDao;
import com.ifpb.edu.model.dao.GrupoDaoImpl;
import com.ifpb.edu.model.dao.publicacao.FeedPublicacaoDAO;
import com.ifpb.edu.model.dao.publicacao.impdb.ComentarioDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.CompartilhaDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.CurteDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.FeedPublicacaoDAOImpDB;
import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Comentario;
import com.ifpb.edu.model.domain.publicacao.FeedPublicacao;
import com.ifpb.edu.model.domain.publicacao.Texto;

public class FeedController implements Command {

	public FeedController() {

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String acao = request.getParameter("acao");
		switch (acao) {
		case "feed":
			feed(request, response);
			break;
		case "comenta":
			comenta(request, response);
			break;
		case "curte":
			curte(request, response);
			break;
		case "compartilha":
			compartilha(request, response);
			break;
		case "publicacoesDoGrupo":
			publicacoesDoGrupo(request,response);			
		default:

		}
	}

	private void compartilha(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		CompartilhaDAOImpDB compartilhaDAO = new CompartilhaDAOImpDB();
		GrupoDaoImpl grupoDAO = new GrupoDaoImpl();
		int usuarioId;
		int publicacaoId;
		try {
			if ((request.getParameter("usuarioid") == null)|| (request.getParameter("textoid") == null)) {
				response.sendRedirect("index.jsp");
				return;
			}
			usuarioId = Integer.parseInt(request.getParameter("usuarioid"));
			publicacaoId = Integer.parseInt(request.getParameter("textoid"));
			String[] grupos = request.getParameterValues("grupo");
			for (String grupo : grupos) {
				int grupoId = grupoDAO.buscaIdPorNome(grupo);								
				if(!compartilhaDAO.existe(usuarioId, publicacaoId, grupoId))
					compartilhaDAO.cria(usuarioId, publicacaoId, grupoId);
			}
			response.sendRedirect(request.getHeader("Referer"));
		}catch (Exception e) {			
			throw new CommandException(500, "Falha ao compartilhar publicação.");
		}
	}

	private void publicacoesDoGrupo(HttpServletRequest request, HttpServletResponse response) {
		Logger log = Logger.getLogger("FeedContoller");
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
			FeedPublicacaoDAO feedPublicacaoDao = new FeedPublicacaoDAOImpDB(usuario);
			GrupoDao grupoDao = new GrupoDaoImpl();
			Grupo grupo;
			grupo = grupoDao.busca(grupoDao.buscaIdPorNome((String) request.getParameter("NomeGrupo")));
			log.info("--> Grupo: "+grupo.toString());
			int numPagina = 1;
			int qtdPag = 1;
			int numPublPag = 5;
			int qtdPub;
			qtdPub = feedPublicacaoDao.quantFeed();
			qtdPag = (int) Math.ceil((double) qtdPub / (double) numPublPag);
			numPagina = (numPagina > qtdPag) ? qtdPag : numPagina;
			numPagina = (numPagina < 1) ? 1 : numPagina;
			List<FeedPublicacao> publicacoesGrupo = feedPublicacaoDao.listaGrupo(grupo, (numPagina - 1) * numPublPag, numPublPag);
			log.info("-->Feed: "+publicacoesGrupo.toString());
			request.getServletContext().getRequestDispatcher("/restrito/feed.jsp").include(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void curte(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		Usuario usuario = null;
		int textoId;
		boolean curtido;
		try {
			usuario = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
			if ((usuario == null) || (request.getParameter("textoid") == null)
					|| (request.getParameter("textocurtido") == null)) {
				response.sendRedirect("index.jsp");
				return;
			}
			curtido = request.getParameter("textocurtido").equals("true");
			textoId = Integer.parseInt(request.getParameter("textoid"));

			CurteDAOImpDB curteDAOImpDB = new CurteDAOImpDB();
			if (!curtido) {
				curteDAOImpDB.cria(textoId, usuario.getId());
			} else {
				curteDAOImpDB.exclui(textoId, usuario.getId());
			}

			response.sendRedirect(request.getHeader("Referer"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(500, "Falha ao curtir publicação.");
		}

	}

	private void feed(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		Usuario usuario = null;
		FeedPublicacaoDAOImpDB feedPublicacaoDAO = null;
		List<FeedPublicacao> feedPublicacao = null;
		int numPagina = 1;
		int qtdPag = 1;
		int numPublPag = 5;
		int qtdPub;
		try {
			usuario = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
			if (usuario == null) {
				response.sendRedirect("index.jsp");
				return;
			}
			if (request.getParameter("pag") != null)
				numPagina = Integer.parseInt(request.getParameter("pag"));
			if (request.getServletContext().getInitParameter("numPublicacoesPagina") != null)
				numPublPag = Integer.parseInt(request.getServletContext().getInitParameter("numPublicacoesPagina"));
			feedPublicacaoDAO = new FeedPublicacaoDAOImpDB(usuario);
			qtdPub = feedPublicacaoDAO.quantFeed();
			qtdPag = (int) Math.ceil((double) qtdPub / (double) numPublPag);			
			numPagina = (numPagina > qtdPag) ? qtdPag : numPagina;
			numPagina = (numPagina < 1) ? 1 : numPagina;
			feedPublicacao = feedPublicacaoDAO.listaFeed((numPagina - 1) * numPublPag, numPublPag);
			feedPublicacaoDAO.carregarComentarios(feedPublicacao);
			request.setAttribute("gruposParticipa",new GrupoDaoImpl().buscarGruposUsuarioParticipa(usuario.getId()));
			request.setAttribute("usuarioId", usuario.getId());
			request.setAttribute("pag", numPagina);
			request.setAttribute("qtdPag", qtdPag);
			request.setAttribute("feedQtd", qtdPub);
			request.setAttribute("feedPublicacao", feedPublicacao);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/restrito/feed.jsp");
			dispatcher.include(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(500, "Falha ao montar o feed");
		}
	}

	private void comenta(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		Usuario usuario = null;
		int textoId;
		try {
			usuario = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
			if (usuario == null) {
				response.sendRedirect("index.jsp");
				return;
			}

			if (request.getParameter("comentario") == null) {
				response.sendRedirect("index.jsp");
				return;
			}

			if (request.getParameter("textoid") == null) {
				response.sendRedirect("index.jsp");
				return;
			}
			try {
				textoId = Integer.parseInt(request.getParameter("textoid"));
			} catch (NumberFormatException e) {
				textoId = -1;
			}
			if (textoId == -1) {
				response.sendRedirect("index.jsp");
				return;
			}
			Texto texto = new Texto();
			texto.setId(textoId);
			new ComentarioDAOImpDB().cria(new Comentario(request.getParameter("comentario"), usuario, texto));
			response.sendRedirect(request.getHeader("Referer"));
		} catch (Exception e) {
			throw new CommandException(500, "Falha ao publicar comentário.");
		}
	}
}
