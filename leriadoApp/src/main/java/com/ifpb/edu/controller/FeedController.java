package com.ifpb.edu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.publicacao.impdb.CompartilhaDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.FeedComentarioDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.FeedPublicacaoDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.FeedComentario;
import com.ifpb.edu.model.domain.publicacao.FeedPublicacao;

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
		default:

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
			usuario = (Usuario)request.getSession(true).getAttribute("usuarioLogado");
			if(usuario==null) {
				response.sendRedirect("index.jsp");
				return;
			}			
			if (request.getParameter("pag")!=null) 
				numPagina = Integer.parseInt(request.getParameter("pag"));			
			if (request.getServletContext().getInitParameter("numPublicacoesPagina")!=null)
				numPublPag = Integer.parseInt(request.getServletContext().getInitParameter("numPublicacoesPagina"));
			feedPublicacaoDAO = new FeedPublicacaoDAOImpDB(usuario);
			qtdPub = feedPublicacaoDAO.quantFeed();
			qtdPag = (int)Math.ceil((double)qtdPub / (double)numPublPag); 
			numPagina = (numPagina<1)?1:numPagina;
			numPagina = (numPagina>qtdPag)?qtdPag:numPagina;			
			feedPublicacao = feedPublicacaoDAO.listaFeed((numPagina-1) * numPublPag, numPublPag);
			feedPublicacaoDAO.carregarComentarios(feedPublicacao);
			request.setAttribute("pag", numPagina);
			request.setAttribute("qtdPag", qtdPag);
			request.setAttribute("feedQtd", qtdPub);
			request.setAttribute("feedPublicacao", feedPublicacao);			
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/restrito/feed.jsp");
			dispatcher.include(request, response);			
		}catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(500, "Falha ao montar o feed");
		}
	}

}
