package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface genérica de comando que será implementada pelos Controladores de Aplicação
 */
public interface Command {

	/** 
	 * Define o método genérico para a execução do comando.
	 * Note que é necessário definir o lançamento das exeções ServletException e IOException
	 * Essas exceções são lançadas durante o redirecionamento de chamadas (chamada forward ao requestDispatcher do HttpServletRequest)
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}
