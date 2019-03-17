package com.ifpb.edu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.jdbc.DataAccessException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.model.dao.GrupoDao;
import com.ifpb.edu.model.dao.GrupoDaoImpl;
/*
 * Objetico do servlet é buscar os grupos que o usuário logado participa 
 * para exibir na página
 * */
@WebServlet("/montarGruposParticipa")
public class GuprosUsuario extends HttpServlet{
	
	private Logger log = Logger.getLogger("GuprosUsuario");
	
	private GrupoDao grupoDao;
	
	public GuprosUsuario() {
		grupoDao = new GrupoDaoImpl();
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		try {
			Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
			List<String> grupos = grupoDao.buscarGruposUsuarioParticipa(usuarioLogado.getId());
			log.info("**** Grupos: "+grupos);
			request.setAttribute("gruposParticipa", grupos);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
