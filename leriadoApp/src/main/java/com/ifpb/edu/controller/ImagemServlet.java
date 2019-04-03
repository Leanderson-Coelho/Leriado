package com.ifpb.edu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.model.dao.GrupoDao;
import com.ifpb.edu.model.dao.GrupoDaoImpl;
import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.jdbc.DataAccessException;

@WebServlet("/imagem")
public class ImagemServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
		GrupoDao grupoDao = new GrupoDaoImpl();
		Grupo grupo = null;
		try {
			grupo = grupoDao.busca(idGrupo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(grupo!=null) {
			String pathReal = request.getSession().getServletContext().getRealPath("");
			File foto = new File(pathReal+"userimg/"+grupo.getFoto());
			if(foto.exists()) {
				response.setContentType("image/jpeg");
				try {
					response.getOutputStream().write(Files.readAllBytes(foto.toPath()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
}
