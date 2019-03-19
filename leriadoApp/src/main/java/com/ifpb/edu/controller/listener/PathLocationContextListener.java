package com.ifpb.edu.controller.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PathLocationContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext sc = servletContextEvent.getServletContext();		
		String path = sc.getRealPath("");		
		String pathImg = sc.getInitParameter("pastaImagensUsuario");
		String pathSaveImg = path + pathImg;
		System.out.println(pathImg);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	
	}
	
		

}
