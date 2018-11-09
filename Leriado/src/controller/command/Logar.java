package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.CrudUsuario;
import model.Usuario;
import model.UsuarioDAO;

/** 
 * Controlador de Aplicação referente à entidade Usuario
 * Note ue ele implementa a interface Command e o método execute definido por ela
 */
public class Logar implements Command {

	/** 
	 * Lógica de negócio do comando de Usuarios.
	 *  
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CrudUsuario crudUsuario = new CrudUsuario();
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		Usuario usuario = crudUsuario.autenticaUsuario(login, senha);
		if (usuario==null) {
			request.setAttribute("erro", "Acesso negado!");
			request.setAttribute("voltar", "'./controller?comando=Login'");
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		} else {
			request.setAttribute("nome", usuario.getNome());
			request.getRequestDispatcher("controller?comando=Home").forward(request, response);
			
		}
	}

}
