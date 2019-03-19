package com.ifpb.edu.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.GrupoDao;
import com.ifpb.edu.model.dao.GrupoDaoImpl;
import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.dao.publicacao.impdb.FotoDAOImpDB;
import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Foto;
import com.ifpb.edu.model.jdbc.DataAccessException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,
maxFileSize = 1024 * 1024 * 10,
maxRequestSize = 1024 * 1024 * 10 * 10,
location = "userimg")
public class GrupoController implements Command{
	
	private UsuarioDao usuarioDao;
	private GrupoDao grupoDao;
	private Logger log = Logger.getLogger("GrupoController");
	
	public GrupoController() {
		usuarioDao = new UsuarioDaoImpl();
		grupoDao = new GrupoDaoImpl();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String acao = request.getParameter("acao");
		log.info(acao);
		switch(acao) {
			case "grupos":
				grupos(request,response);
				break;
			case "adicionarUsuario":
				adicionarUsuario(request,response);
				break;
			case "removerUsuario":
				removerUsuario(request,response);
				break;
			case "gerenciarGrupos":
				gerenciarGrupos(request,response);
				break;
			case "criarGrupo":
				criarGrupo(request,response);
				break;
			case "removerGrupo":
				removerGrupo(request,response);
				break;
		}
	}

	private void removerGrupo(HttpServletRequest request, HttpServletResponse response) {
		try {
			log.info("--->> IDGRUPO: "+request.getParameter("idGrupo"));
			int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
			grupoDao.excluir(idGrupo);		
			response.sendRedirect("restrito/meusGrupos.jsp");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void criarGrupo(HttpServletRequest request, HttpServletResponse response) {
		Logger log = Logger.getLogger("GrupoController");
		String imgPath = (String) request.getServletContext().getAttribute("IMG_FILE"); 
		String nomeGrupo = (String) request.getParameter("nome");
		String descricao = (String) request.getParameter("descricao");
		try {
			if (nomeGrupo == null) {
				String msgErro = "Preencha todos os campos";
				request.setAttribute("msgErro", msgErro);
				response.sendRedirect(request.getHeader("Referer"));
				return;
			}	

			GrupoDao grupoDao = new GrupoDaoImpl();
			FotoDAOImpDB dao = new FotoDAOImpDB();
			Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
			String nomeFoto = "";
			log.info(nomeGrupo);
			for (Part part : request.getParts()) {
				if (part.getContentType() != null) {// se ele tiver um tipo então é porque ele é um arquivo :)
					nomeFoto = dao.nomeFoto();
					// escreve a imagem no HD obs.: o split("/") é pra dividir o tipo do arquivo que
					// vem desta forma image/png
					part.write(imgPath + nomeFoto);															
					Foto f = new Foto();
					f.setArquivo(nomeFoto);
					grupoDao.criar(new Grupo(0,true,LocalDateTime.now(),nomeGrupo,descricao,f.getArquivo()));
					grupoDao.adicionarAdministrador(usuarioLogado.getId(), grupoDao.buscaIdPorNome(nomeGrupo));
					grupoDao.adicionarUsuario(grupoDao.buscaIdPorNome(nomeGrupo), usuarioLogado.getId());
					response.sendRedirect("restrito/meusGrupos.jsp");
					return;
				}
			}
			if (nomeFoto.isEmpty()) {
				String msgErro = "Imagem precisa ser enviada!";
				request.setAttribute("msgErro", msgErro);
			}
			response.sendRedirect(request.getHeader("Referer"));
		} catch (IOException | ServletException | DataAccessException | SQLException e) {
			e.printStackTrace();
		}
	}
	
//	private void criarGrupo(HttpServletRequest request, HttpServletResponse response) {
//		GrupoDao grupoDao = new GrupoDaoImpl();
//		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
//		String nomeGrupo = (String) request.getPar
//	}

	private void gerenciarGrupos(HttpServletRequest request, HttpServletResponse response) {
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
			List<Grupo> gruposUsuarioAdministra = grupoDao.admsGrupo(usuario.getId());
			if(gruposUsuarioAdministra.isEmpty())
				gruposUsuarioAdministra = null;
			request.setAttribute("gruposUsuarioAdministra", gruposUsuarioAdministra);
			request.getServletContext().getRequestDispatcher("/restrito/grupos/gruposGerencia.jsp").include(request, response);
		} catch (DataAccessException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void removerUsuario(HttpServletRequest request, HttpServletResponse response) {
		int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
		try {
			Usuario usuario = usuarioDao.buscarPorEmail(request.getParameter("emailRemover"));
			grupoDao.removerUsuario(idGrupo,usuario.getId());
			response.sendRedirect("restrito/meusGrupos.jsp");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				response.sendRedirect("restrito/meusGrupos.jsp");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void grupos(HttpServletRequest request, HttpServletResponse response) {
		try {
			Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
			List<String> grupos = grupoDao.buscarGruposUsuarioParticipa(usuarioLogado.getId());
			log.info("--> Grupos: "+grupos);
			request.setAttribute("gruposParticipa", grupos);
			request.getServletContext().getRequestDispatcher("/restrito/grupos.jsp").include(request, response);
		} catch (DataAccessException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
