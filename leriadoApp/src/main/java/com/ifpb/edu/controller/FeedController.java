package com.ifpb.edu.controller;

<<<<<<< HEAD
import java.io.File;
import java.io.IOException;
=======
import java.io.IOException;
import java.io.PrintWriter;
>>>>>>> bPublicacao
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ifpb.edu.controller.exception.CommandException;
import com.ifpb.edu.model.dao.publicacao.impdb.ComentarioDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.CompartilhaDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.FeedComentarioDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.FeedPublicacaoDAOImpDB;
import com.ifpb.edu.model.dao.publicacao.impdb.TextoDAOImpDB;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Comentario;
import com.ifpb.edu.model.domain.publicacao.FeedComentario;
import com.ifpb.edu.model.domain.publicacao.FeedPublicacao;
import com.ifpb.edu.model.domain.publicacao.Texto;

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
		case "comenta":
			comenta(request,response);
			break;
		default:

		}
	}

	private void publicacao(HttpServletRequest request, HttpServletResponse response) {
		String initPath = "/home/ian/Projetos_Programas/Java/"; //define local onde será armazenado
		String pathDocLeriado = "Leriado/leriadoApp/WebContent/userimg";
		String titulo = request.getParameter("titulo");
		String conteudo = request.getParameter("conteudo");
		String link = request.getParameter("link");
		
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
	
	private void comenta(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		Usuario usuario = null;		  
		int textoId;
		try {
			usuario = (Usuario)request.getSession(true).getAttribute("usuarioLogado");
			if(usuario==null) {
				response.sendRedirect("index.jsp");
				return;
			}
			if (request.getParameter("comentario")!=null){
				response.sendRedirect("index.jsp");
				return;
			}
			if (request.getParameter("textoid")!=null){
				response.sendRedirect("index.jsp");
				return;
			}
			textoId = Integer.parseInt(request.getParameter("textoid"), -1);
			if (textoId == -1){
				response.sendRedirect("index.jsp");
				return;
			}
			Texto texto = new Texto();
			texto.setId(textoId);
			new ComentarioDAOImpDB().cria(
				new Comentario(
					request.getParameter("comentario"),
					usuario,
					texto));
			
			System.out.println(request.getHeader("Referer"));
			response.sendRedirect(request.getHeader("Referer"));
		}catch (Exception e) {
			throw new CommandException(500, "Falha ao montar o feed");
		}
	}
}
