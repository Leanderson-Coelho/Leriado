package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import model.UsuarioDAO;

/** 
 * Controlador de Aplicação referente à entidade Usuario
 * Note ue ele implementa a interface Command e o método execute definido por ela
 */
public class Login implements Command {

	/** 
	 * Lógica de negócio do comando de Usuarios.
	 *  
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.html").forward(request, response);
	}

}
