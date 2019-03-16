package com.ifpb.edu.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.GrupoDao;
import com.ifpb.edu.model.dao.GrupoDaoImpl;
import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class GrupoController implements Command{
	
	private UsuarioDao usuarioDao;
	private GrupoDao grupoDao;
	
	public GrupoController() {
		usuarioDao = new UsuarioDaoImpl();
		grupoDao = new GrupoDaoImpl();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String acao = request.getParameter("acao");
		switch(acao) {
			case "home":
				home(request,response);
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
		//TODO
		
		
		try {
			response.sendRedirect("restrito/home.jsp");
		} catch (IOException e) {
			// erro 404
			e.printStackTrace();
		}
		
	}

}
