package controller.comand;

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
public class UsuarioCommand implements Command {

	/** 
	 * Lógica de negócio do comando de Usuarios.
	 *  
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*// Instancia a classe DAO responsável por abstrair o acesso a banco (modelo)
		UsuarioDAO usuario = new UsuarioDAO();
		
		// Chama o modelo para recuperar a lista de usuarios
		List<Usuario> usuarios = usuario.listarUsuarios();
		
		// Adiciona um atributo de requisição chamado 'usuarios' para que seja utilizado na visao
		request.setAttribute("usuarios", usuarios);
		
		// Encaminha a chamada para a visão, especificamente para a página usuarios.jsp
		request.getRequestDispatcher("usuarios.jsp").forward(request, response);
			*/	
	}

}
