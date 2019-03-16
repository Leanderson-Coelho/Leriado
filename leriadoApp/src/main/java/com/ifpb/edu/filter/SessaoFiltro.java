package com.ifpb.edu.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="SessaoFilter", urlPatterns= {"/index.jsp"})
public class SessaoFiltro extends HttpFilter{
	private static Logger log = Logger.getLogger("SessaoFilter");

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void filtrarRequisicao(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request.getSession().getAttribute("usuarioLogado") != null) {
			log.severe("--> Requisição inválida");
			response.sendRedirect("restrito/logado.jsp");
		}
		limparHeader(response);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
