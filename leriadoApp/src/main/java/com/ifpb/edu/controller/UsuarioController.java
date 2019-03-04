package com.ifpb.edu.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.domain.Usuario;

public class UsuarioController implements Command{
	private UsuarioDao usuarioDao;
	
	public UsuarioController() {
		usuarioDao = new UsuarioDaoImpl();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String acao = request.getParameter("acao");
		switch(acao) {
			case "criar":
				cadastrar(request,response);
				break;
			case "remover":
				remover(request,response);
				break;
			case "atualizar":
				atualizar(request,response);
			case "buscarPorEmail":
				buscarPorEmail(request,response);
				break;
			case "buscarProId":
				buscarPorId(request,response);
				break;
			case "login":
				login(request,response);
				break;
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			Usuario usuario = usuarioDao.buscarPorEmail(login);
			if(usuarioDao.login(login, senha)) {
				usuario.setSenha("");
				request.getSession(true).setAttribute("usuarioLogado", usuario);
				response.sendRedirect("logado.jsp");
			}else {
				throw new CommandException(401, "Senha ou email inválido");
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buscarPorId(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void buscarPorEmail(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void atualizar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void remover(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
}
