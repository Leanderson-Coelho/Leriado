package com.ifpb.edu.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.publicacao.impdb.CompartilhaDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;

public class PublicacaoController implements Command{
	
	public PublicacaoController() {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String acao = request.getParameter("acao");
		switch(acao) {
		case "feed":
			feed(request,response);
			break;
		default:
			
		}		
	}

	private void feed(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		CompartilhaDAOImpDB compartilhaDAO = new CompartilhaDAOImpDB();
		Usuario usuario = null;
		int numPagina = 0;
		int numPublPag = 4;
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
				numPublPag = 4;//Integer.parseInt(request.getServletContext().getInitParameter("numPublicacoesPagina"));
			qtdPub = compartilhaDAO.quantFeed(usuario);
			qtdPub = (int)Math.ceil((double)qtdPub / (double)numPublPag);
			request.setAttribute("pag", numPagina);
			request.setAttribute("feed", compartilhaDAO.feed(usuario, numPagina * numPublPag, numPublPag));
			request.setAttribute("feedQtd", qtdPub);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/restrito/publicacao.jsp");
			dispatcher.include(request, response);			
		}catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(500, "Falha ao montar o feed");
		}
	}
	

}