package com.ifpb.edu.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.GrupoDaoImpl;
import com.ifpb.edu.model.dao.publicacao.impdb.CompartilhaDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.FotoDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.LinkDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.NoticiaDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.PublicacaoDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Foto;
import com.ifpb.edu.model.domain.publicacao.Link;
import com.ifpb.edu.model.domain.publicacao.Noticia;
import com.ifpb.edu.model.domain.publicacao.Publicacao;
import com.ifpb.edu.model.jdbc.DataAccessException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10
		* 10)
public class PublicacaoController implements Command {

	public PublicacaoController() {
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String acao = request.getParameter("acao");
		switch (acao) {
		case "mensagem":
			mensagem(request, response);
			break;
		case "link":
			link(request, response);
			break;
		case "noticia":
			noticia(request, response);
			break;
		case "foto":
			foto(request, response);
			break;
		case "publica":
			publica(request,response);
			break;
		default:
		}
	}

	private void publica(HttpServletRequest request, HttpServletResponse response) {
		//
		
	}

	private void foto(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String imgPath = (String) request.getServletContext().getAttribute("IMG_FILE");		
		String conteudo = request.getParameter("conteudo");
		
		try {
						   

			if (conteudo == null) {
				String msgErro = "Erro no envio!!";
				request.setAttribute("msgErro", msgErro);
				response.sendRedirect(request.getHeader("Referer"));
				return;
			}
			
			FotoDAOImpDB dao = new FotoDAOImpDB();
			Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
			String nomeFoto = "";

			for (Part part : request.getParts()) {
				if (part.getContentType() != null) {// se ele tiver um tipo então é porque ele é um arquivo :)
					nomeFoto = dao.nomeFoto();
					// escreve a imagem no HD obs.: o split("/") é pra dividir o tipo do arquivo que
					// vem desta forma image/png
					part.write(imgPath + nomeFoto);															
					Foto f = new Foto(conteudo, usuarioLogado, 1, nomeFoto);
					dao.cria(f);					
					compartilha(f.getId(),request,response);
					
				}
			}
			if (nomeFoto.isEmpty()) {
				String msgErro = "Imagem precisa ser enviada!";
				request.setAttribute("msgErro", msgErro);
			}
			response.sendRedirect(request.getHeader("Referer"));
		} catch (IOException | ServletException | DataAccessException e) {
			e.printStackTrace();
		}
	}

	private void noticia(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String imgPath = (String) request.getServletContext().getAttribute("IMG_FILE");		
		String titulo = request.getParameter("titulo");
		String conteudo = request.getParameter("conteudo");
		try {
			if (titulo.isEmpty() || titulo == null || conteudo.isEmpty() || conteudo == null) {
				String msgErro = "Preencha os campos obrigatórios!";
				request.setAttribute("msgErro", msgErro);
				response.sendRedirect(request.getHeader("Referer"));
				return;
			}
			
			NoticiaDAOImpDB dao = new NoticiaDAOImpDB();
			FotoDAOImpDB fotoDao = new FotoDAOImpDB();
			List<Foto> fotos = new ArrayList<>();
			Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

			String fotoNome = "";
			for (Part part : request.getParts()) {				
				if (part.getContentType() != null) {// se ele tiver um tipo então é porque ele é um arquivo :)
					fotoNome = fotoDao.nomeFoto();
					// escreve a imagem no HD obs.: o split("/") é pra dividir o tipo do arquivo que
					// vem desta forma image/png
					part.write(imgPath + fotoNome);					
					Foto f = new Foto("", usuarioLogado, 1, fotoNome);
					fotos.add(f);
					//compartilha(f.getId(),request,response);
				}
			}
			Noticia n = new Noticia(titulo, conteudo, usuarioLogado, 1, fotos);
			dao.cria(n);
			compartilha(n.getId(),request,response);
			response.sendRedirect(request.getHeader("Referer"));			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(500,"Falha ao compartilhar notícia");
		}
	}

	private void link(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String conteudo = request.getParameter("conteudo");
		String link = request.getParameter("link");
		try {
			if (link.isEmpty() || link == null) {
				String msgErro = "Informe um link!";
				request.setAttribute("msgErro", msgErro);
				response.sendRedirect(request.getHeader("Referer"));
				return;
			}			
			Link l = new Link(conteudo, (Usuario) request.getSession().getAttribute("usuarioLogado"), 1, link);
			LinkDAOImpDB dao = new LinkDAOImpDB();			
			dao.cria(l);			
			compartilha(l.getId(),request,response);			
			response.sendRedirect(request.getHeader("Referer"));
		} catch (DataAccessException | IOException e) {
			e.printStackTrace();
		}

	}

	private void mensagem(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		String conteudo = request.getParameter("conteudo");
		try {
			if (conteudo.isEmpty() || conteudo == null) {
				String msgErro = "Informe algo!";
				request.setAttribute("msgErro", msgErro);
				response.sendRedirect(request.getHeader("Referer"));
				return;
			}
			PublicacaoDAOImpDB dao = new PublicacaoDAOImpDB();
			Publicacao p = new Publicacao(conteudo, (Usuario) request.getSession().getAttribute("usuarioLogado"), 1);			
			dao.cria(p);			
			compartilha(p.getId(),request,response);
			response.sendRedirect(request.getHeader("Referer"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void compartilha(int publicacaoId, HttpServletRequest request, HttpServletResponse response) throws CommandException {
		CompartilhaDAOImpDB compartilhaDAO = new CompartilhaDAOImpDB();
		GrupoDaoImpl grupoDAO = new GrupoDaoImpl();
		int usuarioId;
			try {
			if (request.getParameter("usuarioid") == null) {
				response.sendRedirect("index.jsp");
				return;
			}			
			usuarioId = Integer.parseInt(request.getParameter("usuarioid"));
			String[] grupos = request.getParameterValues("grupo");
			for (String grupo : grupos) {
				int grupoId = grupoDAO.buscaIdPorNome(grupo);								
				if(!compartilhaDAO.existe(usuarioId, publicacaoId, grupoId))
					compartilhaDAO.cria(usuarioId, publicacaoId, grupoId);				
			}	
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(500, "Falha ao compartilhar publicação.");
		}
	}
}
