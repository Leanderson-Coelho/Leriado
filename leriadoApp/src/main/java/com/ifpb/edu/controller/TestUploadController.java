package com.ifpb.edu.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/testeUpload")
@MultipartConfig(
		fileSizeThreshold = 1024*1024*10,
		maxFileSize = 1024*1024*10,
		maxRequestSize = 1024*1024*10*10
		)
public class TestUploadController extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String titulo = request.getParameter("titulo");
			String conteudo = request.getParameter("conteudo");
			String link = request.getParameter("link");
			System.out.println(titulo+"\n"+conteudo+"\n"+link);
			
			//Não esquecer de modificar o path de acordo com a sua máquina
			File file = new File("/home/ian/Projetos_Programas/Java/Leriado/leriadoApp/WebContent/userimg");//define local onde será armazenado
			if(!file.exists()) {
				file.mkdir();		
			}
			
			String fileName = "";
			Integer count=0;
			for(Part part:request.getParts()) {
				if(part.getContentType()!=null) {//se ele tiver um tipo então é porque ele é um arquivo :)
					fileName = (count++).toString();//gerar nome da imagem 
					//escreve a imagem no HD    obs.: o split("/") é pra dividir o tipo do arquivo que vem desta forma image/png
					part.write("/home/ian/Projetos_Programas/Java/Leriado/leriadoApp/WebContent/userimg"+
							File.separator+fileName+"."+part.getContentType().split("/")[1]);
				}
			}
			
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}
}