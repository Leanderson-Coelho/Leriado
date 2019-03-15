package com.ifpb.edu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * HttpFilter - classe que implementa um Filtro genérico de servlet.
 * O objetivo da classe é servir como um filtro para o protocolo Http
 * fazendo um casting dos parametros 'request' e 'response' do tipo 
 * ServletRequest e ServletResponse para HttpServletRequest e HttpServletResponse
 * */
public abstract class HttpFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		filtrarRequisicao((HttpServletRequest)request,(HttpServletResponse)response, chain);
	}
	
	/* O método abstrato obriga as classes que extender de HttpFilter implementar o seu doFilter que foi renomeado para filtrarRequisição */
	public abstract void filtrarRequisicao(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;
	
}
