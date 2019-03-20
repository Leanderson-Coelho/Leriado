package com.ifpb.edu.controller.listener;

import java.io.File;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PathLocationContextListener implements ServletContextListener{
	
	private static Logger log = Logger.getLogger("PathLocationContextListener");

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {		
		ServletContext sc = servletContextEvent.getServletContext();		
		String path = sc.getRealPath("");		
		String pathImg = sc.getInitParameter("pastaImagensUsuario");		
		String pathSaveImg = path + pathImg + File.separator;
		String urlImg = sc.getContextPath() + File.separator + pathImg + File.separator; 
		File file = new File(pathSaveImg);
		if(!file.exists()) {
			file.mkdirs();
		}
		sc.setAttribute("IMG_FILE", pathSaveImg);		
		sc.setAttribute("pastaImagensUsuario", urlImg);
		log.info(" DIRETORIO DE IMAGENS "+ pathSaveImg);
		log.info(" URL DE IMAGENS "+ urlImg);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	
	}
	
		

}
