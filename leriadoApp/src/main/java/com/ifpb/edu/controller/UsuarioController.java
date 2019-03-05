package com.ifpb.edu.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
			case "cadastrar":
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
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		try {
			usuarioDao.remover(usuario.getId());
			request.getSession().invalidate();
			response.sendRedirect("index.html");
		} catch (SQLException e) {
			//erro 501
			e.printStackTrace();
		} catch (IOException e) {
			//erro 404
			e.printStackTrace();
		}
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) {
		
		Usuario usuario = new Usuario();
		
		String email = request.getParameter("emailAnterior")+request.getParameter("emailPosterior");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String sobrenome = request.getParameter("sobrenome");
		String sexo = request.getParameter("sexo");
		String data = request.getParameter("data");
		String telefone = request.getParameter("telefone");
		String cep = request.getParameter("cep");
		String cidade = request.getParameter("cidade");
		String rua = request.getParameter("rua");
		String estado = request.getParameter("estado");
		String numero = request.getParameter("numero");
		
		try {
			if(usuarioDao.buscarPorEmail(email)!=null) {
				//email já cadastrado ;-;
			}
		} catch (SQLException e1) {
			//erro 501
		}
		
		if(senha.length()<8) {
			//senha menor que 8 caracteres
		}
		
		if(nome.isEmpty() && sobrenome.isEmpty() && sexo.isEmpty()) {
			//preencha os campos obrigatórios
		}
		
		if(!telefone.isEmpty() && telefone.length()<14) {
			//formatação do numero de telefone inválido
		}
		
		if(!cep.isEmpty() && cep.length()<9) {
			//formatação do cep inválido 
		}
		
		if(senha.equals(request.getParameter("confirma-senha"))) {
			//senha não corresponde a confirmação
		}
		
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			usuario.setDatanasc(LocalDate.parse(data, formatter));			
		}catch(DateTimeParseException e) {
			//formatação da data inválida
		}
		
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setSenha(senha);
		usuario.setSexo(sexo);
		usuario.setTelefone(telefone);
		usuario.setCidade(cidade);
		usuario.setRua(rua);
		usuario.setEstado(estado);
		usuario.setNumero(numero);
		usuario.setCep(cep);
		usuario.setAcesso(1);
		
		try {
			usuarioDao.criar(usuario);
		} catch (SQLException e) {
			//erro 501
		}
		
		request.setAttribute("usuario", usuario);
	}
}
