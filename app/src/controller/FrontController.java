package controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.Command;

@WebServlet("/controller")
public class FrontController extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupera o parâmetro com o nome do comando a ser instanciado
		String comando = request.getParameter("comando");
		try {
			/** 
			 * Cria uma nova instância do comando que foi passado por parâmetro (usando a API Java Reflection). 
			 * Note que para tal, é necessário passar como parâmetro o caminho completo da classe. 
			 * Como ela se encontra no pacote 'controller.command', essa informação precisa ser adicionada antes do nome do comando.
			 */
			Command command = (Command) Class.forName("controller.command."+comando).newInstance();
			// Chama o método geral da interface comando que é implementado por cada comando criado na aplicação
			command.execute(request, response);
		} catch (InstantiationException e) {
			// Prenche o atributo que será usado como ${erro} na visão
			request.setAttribute("voltar", "'./controller?comando=Home'");
			request.setAttribute("erro", "Erro interno - Ligue para o programador!");
			// Encaminha a requisição para a página de erro em caso de exceção
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		} catch (IllegalAccessException e) {
			// Prenche o atributo que será usado como ${erro} na visão
			request.setAttribute("voltar", "'./controller?comando=Home'");
			request.setAttribute("erro", "Acesso negado!");
			// Encaminha a requisição para a página de erro em caso de exceção
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			// Prenche o atributo que será usado como ${erro} na visão
			request.setAttribute("voltar", "'./controller?comando=Home'");
			request.setAttribute("erro", "404 - Página não encontrada!");
			// Encaminha a requisição para a página de erro em caso de exceção
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	

}
