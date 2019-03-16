package com.ifpb.edu.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.GrupoDao;
import com.ifpb.edu.model.dao.GrupoDaoImpl;
import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.jdbc.DataAccessException;


@WebServlet("/GrupoController")
public class GrupoController extends HttpServlet implements Command{
	
	private UsuarioDao usuarioDao;
	private GrupoDao grupoDao;
	private Logger log = Logger.getLogger("GrupoController");
	
	public GrupoController() {
		usuarioDao = new UsuarioDaoImpl();
		grupoDao = new GrupoDaoImpl();
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/montarGruposParticipa").include(request, response);
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			request.getRequestDispatcher("restrito/home.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			// erro 404
			e.printStackTrace();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String acao = request.getParameter("acao");
		switch(acao) {
			case "home":
				home(request,response);
				break;
			case "gerarGrupos":
				gerarGrupos(request, response);
				break;
			case "adicionarUsuario":
				adicionarUsuario(request,response);
				break;
		}
	}

	private void adicionarUsuario(HttpServletRequest request, HttpServletResponse response) {
		Usuario novoUsuarioDGrupo;
		int idGrupo = -1;
		try {
			novoUsuarioDGrupo = usuarioDao.buscarPorEmail(request.getParameter("email"));
			if(novoUsuarioDGrupo == null) {
				response.sendRedirect("restrito/home.jsp");
				request.setAttribute("msg", new String("Email inválido"));
			}else {
				try {
					idGrupo = grupoDao.buscaIdPorNome(request.getParameter("nomeGrupo"));
				} catch (DataAccessException e1) {
					request.setAttribute("msg", new String("Grupo não identificado"));
				}
				try {	
					grupoDao.adicionarUsuario(idGrupo, novoUsuarioDGrupo.getId());
				} catch (SQLException e) {
					request.setAttribute("msg", new String("Usuário já participa do grupo"));
					e.printStackTrace();
					response.sendRedirect("restrito/home.jsp");
					return;
				}
				
				request.setAttribute("msg", new String("Usuário adicionado"));
				response.sendRedirect("restrito/home.jsp");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void home(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

	private void gerarGrupos(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

}
