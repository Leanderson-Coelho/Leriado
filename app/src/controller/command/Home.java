package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home implements Command {

	/** 
	 * Lógica de negócio do comando de Usuarios.
	 *  
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		request.getRequestDispatcher("home.jsp").forward(request, response);

	}
}
