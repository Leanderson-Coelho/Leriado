package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.postgresql.util.PSQLException;

import model.Usuario;
import model.UsuarioDAO;
import model.UsuarioDAOBD;

/**
 * Controlador de Aplicação referente à entidade Usuario Note ue ele implementa
 * a interface Command e o método execute definido por ela
 */
public class Login implements Command {

	/**
	 * Lógica de negócio do comando de Usuarios.
	 * @throws ClassNotFoundException 
	 * 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
		if (request.getParameter("cadastrar")!=null && request.getParameter("cadastrar").equals("Leriar")) {
			String emailA = request.getParameter("emailAnterior");
			String emailP = request.getParameter("emailPosterior");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String sobrenome = request.getParameter("sobrenome");
			String sexo = request.getParameter("sexo");
			String data = request.getParameter("data");
			Integer acesso = 1;
			String telefone = request.getParameter("telefone");
			String rua = request.getParameter("rua");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String numero = request.getParameter("numero");
			String cep = request.getParameter("cep");
			Usuario u = new Usuario(1, true, emailA+emailP, senha, nome, sobrenome, sexo, data, acesso, telefone, rua, cidade, estado, numero, cep);
			System.out.println(u);
			UsuarioDAO dao = new UsuarioDAO();
			dao.salvar(u);
		}
		request.getRequestDispatcher("login.html").forward(request, response);
	}

}
