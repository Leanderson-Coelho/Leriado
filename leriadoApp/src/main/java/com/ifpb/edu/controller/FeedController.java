package com.ifpb.edu.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.publicacao.impdb.CompartilhaDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.FeedComentarioDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.FeedPublicacaoDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.LinkDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.PublicacaoDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.FeedComentario;
import com.ifpb.edu.model.domain.publicacao.FeedPublicacao;
import com.ifpb.edu.model.domain.publicacao.Link;
import com.ifpb.edu.model.domain.publicacao.Publicacao;
import com.ifpb.edu.model.jdbc.DataAccessException;

@MultipartConfig(
		fileSizeThreshold = 1024*1024*10,
		maxFileSize = 1024*1024*10,
		maxRequestSize = 1024*1024*10*10
		)
public class FeedController implements Command {

	public FeedController() {

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String acao = request.getParameter("acao");
		switch (acao) {
		case "feed":
			feed(request, response);
			break;
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
		default:

		}
	}

	private void foto(HttpServletRequest request, HttpServletResponse response) {
		String initPath = "/home/ian/Projetos_Programas/Java/"; //define local onde será armazenado
		String pathDocLeriado = "Leriado/leriadoApp/WebContent/userimg";
		String conteudo = request.getParameter("conteudo");
		File file = new File(initPath+pathDocLeriado);
		if(!file.exists()) {
			file.mkdir();		
		}
		
		Integer id = 0;
		try {
			for(Part part:request.getParts()) {
				if(part.getContentType()!=null) {//se ele tiver um tipo então é porque ele é um arquivo :)
					//escreve a imagem no HD    obs.: o split("/") é pra dividir o tipo do arquivo que vem desta forma image/png
					part.write(initPath+pathDocLeriado+File.separator+(id++).toString()+"."+part.getContentType().split("/")[1]);
				}
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
	}

	private void noticia(HttpServletRequest request, HttpServletResponse response) {
		
		String initPath = "/home/ian/Projetos_Programas/Java/"; //define local onde será armazenado
		String pathDocLeriado = "Leriado/leriadoApp/WebContent/userimg";
		String titulo = request.getParameter("titulo");
		String conteudo = request.getParameter("conteudo");
		File file = new File(initPath+pathDocLeriado);
		if(!file.exists()) {
			file.mkdir();		
		}
		
		Integer id = 0;
		try {
			for(Part part:request.getParts()) {
				if(part.getContentType()!=null) {//se ele tiver um tipo então é porque ele é um arquivo :)
					//escreve a imagem no HD    obs.: o split("/") é pra dividir o tipo do arquivo que vem desta forma image/png
					part.write(initPath+pathDocLeriado+File.separator+(id++).toString()+"."+part.getContentType().split("/")[1]);
				}
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
		
	}

	private void link(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		String conteudo = request.getParameter("conteudo");
		String link = request.getParameter("link");
		if(link.isEmpty() || link==null) {
			String msgErro = "Informe um link!";
			request.setAttribute("msgErro", msgErro);
			feed(request, response);
			return;
		}
		
		Link l = new Link(conteudo, (Usuario)request.getSession().getAttribute("usuarioLogado"), 1, 1, link);
		LinkDAOImpDB dao = new LinkDAOImpDB();
		try {
			dao.cria(l);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		feed(request, response);
		
	}

	private void mensagem(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		String conteudo = request.getParameter("conteudo");
		if(conteudo.isEmpty() || conteudo==null) {
			String msgErro = "Informe algo!";
			request.setAttribute("msgErro", msgErro);
			feed(request, response);
			return;
		}
		PublicacaoDAOImpDB dao = new PublicacaoDAOImpDB();
		Publicacao p = new Publicacao(conteudo, (Usuario)request.getSession().getAttribute("usuarioLogado"), 1, 1);
		try {
			dao.cria(p);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		feed(request, response);
	}

	private void feed(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		Usuario usuario = null;
		FeedPublicacaoDAOImpDB feedPublicacaoDAO = null;
		List<FeedPublicacao> feedPublicacao = null; 
		int numPagina = 1;
		int qtdPag = 1;
		int numPublPag = 5;
		int qtdPub;
		try {						
			usuario = (Usuario)request.getSession(true).getAttribute("usuarioLogado");
			if(usuario==null) {
				response.sendRedirect("index.jsp");
				return;
			}			
			if (request.getParameter("pag")!=null) 
				numPagina = Integer.parseInt(request.getParameter("pag"));			
			if (request.getServletContext().getInitParameter("numPublicacoesPagina")!=null)
				numPublPag = Integer.parseInt(request.getServletContext().getInitParameter("numPublicacoesPagina"));
			feedPublicacaoDAO = new FeedPublicacaoDAOImpDB(usuario);
			qtdPub = feedPublicacaoDAO.quantFeed();
			qtdPag = (int)Math.ceil((double)qtdPub / (double)numPublPag); 
			numPagina = (numPagina<1)?1:numPagina;
			numPagina = (numPagina>qtdPag)?qtdPag:numPagina;			
			feedPublicacao = feedPublicacaoDAO.listaFeed((numPagina-1) * numPublPag, numPublPag);
			feedPublicacaoDAO.carregarComentarios(feedPublicacao);
			request.setAttribute("pag", numPagina);
			request.setAttribute("qtdPag", qtdPag);
			request.setAttribute("feedQtd", qtdPub);
			request.setAttribute("feedPublicacao", feedPublicacao);			
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/restrito/feed.jsp");
			dispatcher.include(request, response);			
		}catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(500, "Falha ao montar o feed");
		}
	}

}
