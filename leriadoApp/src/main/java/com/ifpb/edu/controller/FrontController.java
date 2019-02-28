package com.ifpb.edu.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Leriado")
public class FrontController extends HttpServlet{
	
	private void execute(HttpServletRequest request,HttpServletResponse response) {
		try {
			String commandName = request.getParameter("command");
			Command command = (Command) Class.forName(this.getClass().getPackage().getName()+"."+commandName).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		//TODO
	}
	
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		execute(request,response);		
	}
	
	@Override
	public void doDelete(HttpServletRequest request,HttpServletResponse response) {
		//TODO
	}
	
	@Override
	public void doPut(HttpServletRequest request,HttpServletResponse response) {
		//TODO
	}
}