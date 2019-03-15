package com.ifpb.edu.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="AutenticacaoFilter", urlPatterns = "/restrito/*")
public class AutenticacaoFilter extends HttpFilter{
	private static Logger log = Logger.getLogger("AutenticacaoFilter");

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("Iniciou AutenticacaoFilter");
		
	}
	
	@Override
	public void filtrarRequisicao(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("filtrando requisição");
		if(request.getSession().getAttribute("usuarioLogado") == null) {
			request.getSession().setAttribute("msgErro", new String("É necessário efetuar o login para acessar a página!"));
			response.sendRedirect("/leriadoApp/index.jsp");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
