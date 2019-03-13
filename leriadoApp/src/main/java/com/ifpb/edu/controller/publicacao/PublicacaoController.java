package com.ifpb.edu.controller.publicacao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.controller.Command;
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
		}		
	}

	private void feed(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		CompartilhaDAOImpDB compartilhaDAO = new CompartilhaDAOImpDB();
		Usuario usuario = null;
		List<Compartilha> comps = new ArrayList<Compartilha>();
		try {
			int numPagina = Integer.parseInt(request.getParameter("pag"));
			usuario = (Usuario)request.getAttribute("usuarioLogado");			
			comps = compartilhaDAO.feed(usuario, numPagina, 10);
			request.setAttribute("feed", comps);
			response.sendRedirect("feed.jsp");
		}catch (Exception e) {
			throw new CommandException(500, "Falha ao montar o feed");
		}
	}
	

}
