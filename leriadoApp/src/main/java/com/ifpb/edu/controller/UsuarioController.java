package com.ifpb.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;

public class UsuarioController implements Command{
	private UsuarioDao usuarioDao;
	
	public UsuarioController() {
		usuarioDao = new UsuarioDaoImpl();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
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

	private void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
