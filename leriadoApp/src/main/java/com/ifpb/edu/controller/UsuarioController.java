package com.ifpb.edu.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.validadores.Validator;

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
				break;
			case "buscarPorEmail":
				buscarPorEmail(request,response);
				break;
			case "buscarProId":
				buscarPorId(request,response);
				break;
			case "login":
				login(request,response);
				break;
			case "logout":
				logout(request,response);
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
				response.sendRedirect("restrito/logado.jsp");
			}else {
				request.setAttribute("erro", "Senha ou login inválido");
				request.getRequestDispatcher("index.jsp").forward(request, response);
//				throw new CommandException(401, "Senha ou email inválido");
			}
		} catch (SQLException | IOException | ServletException e) {
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
		Usuario usuario = new Usuario();
		String email = request.getParameter("email");
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		List<String> msgsErro = Validator.validaTodos(nome, sobrenome, email, senha, sexo, data, telefone, numero, cep, formatter);
		try {
			if(usuarioDao.buscarPorEmail(email)!=null) {
				msgsErro.add(2, "Email já cadastrado");
			}
		} catch (SQLException e1) {
			//erro 501
		}
		for(String msgErro : msgsErro) {
			if(!(msgErro==null)) {
				request.setAttribute("msgsErro", msgsErro);
				try {
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				} catch (ServletException | IOException e) {
					//erro 501
				}
			}
		}
				
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setSenha(senha);
		usuario.setSexo(sexo);
		usuario.setTelefone(telefone);
		try {
			usuario.setDatanasc(LocalDate.parse(data, formatter));			
		}catch(DateTimeParseException e) {
			//formatação da data inválida
		}
		usuario.setCidade(cidade);
		usuario.setRua(rua);
		usuario.setEstado(estado);
		usuario.setNumero(numero);
		usuario.setCep(cep);
		usuario.setAcesso(1);
		usuario.setAtivo(true);
		usuario.setId(((Usuario)request.getSession().getAttribute("usuarioLogado")).getId());
		
		try {
			usuarioDao.atualizar(usuario, usuario.getId());
		} catch (SQLException e) {
			// erro 501
			e.printStackTrace();
		}
		try {
			response.sendRedirect("restrito/logado.jsp");
		} catch (IOException e) {
			// erro 401
			e.printStackTrace();
		}
		
	}

	private void remover(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		try {
			usuarioDao.remover(usuario.getId());
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
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
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String sobrenome = request.getParameter("sobrenome");
		String sexo = request.getParameter("sexo");
		String dataNasc = request.getParameter("data");
		String telefone = request.getParameter("telefone");
		String cep = request.getParameter("cep");
		String cidade = request.getParameter("cidade");
		String rua = request.getParameter("rua");
		String estado = request.getParameter("estado");
		String numero = request.getParameter("numero");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setSenha(senha);
		usuario.setSexo(sexo);
		try {
			usuario.setDatanasc(LocalDate.parse(dataNasc, formatter)); 			
		}catch(DateTimeParseException e) {
			usuario.setDatanasc(null);
		}
		usuario.setTelefone(telefone);
		usuario.setCidade(cidade);
		usuario.setRua(rua);
		usuario.setEstado(estado);
		usuario.setNumero(numero);
		usuario.setCep(cep);
		usuario.setAcesso(1);
		request.setAttribute("usuario", usuario);
		
		List<String> msgsErro = Validator.validaTodos(nome, sobrenome, email, senha, sexo, dataNasc, telefone, numero, cep, formatter);
		try {
			if(usuarioDao.buscarPorEmail(email)!=null) {
				msgsErro.add(2, "Email já cadastrado");
			}
		} catch (SQLException e1) {
			//erro 501
		}
		
		for(String msgErro : msgsErro) {
			if(!(msgErro==null)) {
				request.setAttribute("msgErro", msgErro);
				try {
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				} catch (ServletException | IOException e) {
					//erro 501
				}
			}
		}
		
		/*
		for(String msgErro : msgsErro) {
			if(!(msgErro==null)) {
				request.setAttribute("msgsErro", msgsErro);
				try {
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				} catch (ServletException | IOException e) {
					//erro 501
				}
			}
		}
		*/
		try {
			usuarioDao.criar(usuario);
			request.getRequestDispatcher("restrito/logado.jsp").forward(request, response);
			return;
		} catch (SQLException | ServletException | IOException e) {
			//erro 501
		}
		
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			// erro 401
			e.printStackTrace();
		}
		
	}
}
