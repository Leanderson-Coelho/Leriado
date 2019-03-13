package com.ifpb.edu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.publicacao.impdb.CompartilhaDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Compartilha;
import com.ifpb.edu.model.jdbc.DataAccessException;

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
		List<Compartilha> comps = new ArrayList<Compartilha>();
		try {			
			int numPagina = 0;
			if (request.getParameter("pag")!=null) 
				numPagina = Integer.parseInt(request.getParameter("pag"));			
			usuario = (Usuario)request.getSession(true).getAttribute("usuarioLogado");
			if(usuario==null)
				response.sendRedirect("index.jsp");
			
			comps = compartilhaDAO.feed(usuario, numPagina, 10);			
			request.setAttribute("feed", comps);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/publicacao.jsp");
			dispatcher.include(request, response);			
		}catch (Exception e) {
			throw new CommandException(500, "Falha ao montar o feed");
		}
	}
	

}
