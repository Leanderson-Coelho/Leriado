package com.ifpb.edu.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.dao.publicacao.FotoDAO;
import com.ifpb.edu.model.dao.publicacao.impdb.FotoDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Foto;
import com.ifpb.edu.model.jdbc.DataAccessException;
import com.ifpb.edu.validadores.Validator;

public class UsuarioController implements Command{
	private UsuarioDao usuarioDao;
	private FotoDAO fotoDao;
	
	private static Logger log = Logger.getLogger("UsuarioController");
	
	public UsuarioController() {
		usuarioDao = new UsuarioDaoImpl();
		fotoDao = new FotoDAOImpDB();
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
			case "fotoPerfil":
				atualizarFotoPerfil(request, response);
				break;
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			log.info("------->  Verificando login");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			Usuario usuario = usuarioDao.buscarPorEmail(login);
			if(usuarioDao.login(login, senha)) {
				usuario.setSenha("");
				request.getSession(true).setAttribute("usuarioLogado", usuario);
				Foto fotoPerfil = fotoDao.buscarFotoPerfil(usuario);
				if(fotoPerfil==null) {
					fotoPerfil = new Foto();
					fotoPerfil.setArquivo(request.getServletContext().getInitParameter("FotoPerfilDefault"));
				}
				request.getSession(true).setAttribute("fotoPerfil",fotoPerfil);
				response.sendRedirect("restrito/home.jsp");
			}else {
				request.setAttribute("erro", "Senha ou login inválido");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (SQLException | IOException | ServletException | DataAccessException  e) {
			// TOD@importO Auto-generated catch block
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
		String email = ((Usuario)request.getSession().getAttribute("usuarioLogado")).getEmail();
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
		for(String msgErro : msgsErro) {
			if(msgErro!=null) {
				request.getSession().setAttribute("msgErro", msgErro);
//				request.setAttribute("msgErro", msgErro);
				try {
//					request.getRequestDispatcher("/restrito/atualizar.jsp").forward(request, response);
					response.sendRedirect("restrito/atualizar.jsp");
					return;
				} catch (IOException e) {
					e.printStackTrace();
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
			usuario.setDatanasc(null);
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
			request.getSession().setAttribute("msgErro", null);
			request.getSession(true).setAttribute("usuarioLogado", usuario);
			response.sendRedirect("restrito/home.jsp");
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
			e1.printStackTrace();
			//erro 501
		}
		
		for(String msgErro : msgsErro) {
			if(msgErro!=null) {
				request.setAttribute("msgErro", msgErro);
				try {
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				} catch (ServletException | IOException e) {
					e.printStackTrace();
					//erro 501
				}
			}
		}
		
		try {
			Foto fotoPerfil = fotoDao.buscarFotoPerfil(usuario);
			if(fotoPerfil==null) {
				fotoPerfil = new Foto();
				fotoPerfil.setArquivo(request.getServletContext().getInitParameter("FotoPerfilDefault"));
			}
			request.getSession(true).setAttribute("fotoPerfil",fotoPerfil);
			usuarioDao.criar(usuario);
			request.getSession().setAttribute("usuarioLogado", usuarioDao.buscarPorEmail(usuario.getEmail()));
			response.sendRedirect("restrito/home.jsp");
			return;
		} catch (SQLException | IOException | DataAccessException e) {
			e.printStackTrace();
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
	private void atualizarFotoPerfil(HttpServletRequest request, HttpServletResponse response) {
		String initPath = "/home/ian/Projetos_Programas/Java/"; 
		String pathDocLeriado = "/Leriado/leriadoApp/WebContent/userimg";
		
		File file = new File(initPath + pathDocLeriado);
		if (!file.exists()) {
			file.mkdir();
		}

		FotoDAOImpDB dao = new FotoDAOImpDB();
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		String nomeFoto = "";

		try {
			for (Part part : request.getParts()) {
				if (part.getContentType() != null) {// se ele tiver um tipo então é porque ele é um arquivo :)
					nomeFoto = dao.nomeFoto();
					part.write(initPath + pathDocLeriado + File.separator + nomeFoto);															
					Foto f = new Foto("", usuarioLogado, 1, nomeFoto);
					dao.criaFotoPerfil(usuarioLogado, f);
					request.getSession().setAttribute("fotoPerfil", nomeFoto);
				}
			}
			response.sendRedirect(request.getHeader("Referer"));
		} catch (IOException | ServletException | DataAccessException e) {
			e.printStackTrace();
		}
	}	
}
