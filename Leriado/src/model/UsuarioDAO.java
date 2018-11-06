package model;

import java.util.Arrays;
import java.util.List;

/** 
 * Classe de acesso aos dados dos usuários
 *
 */
public class UsuarioDAO {
	
	/** 
	 * Método que lista usuários que estão no banco
	 * 
	 * @TODO Usar conexão JDBC para puxar os dados de um banco ao invés de criar manualmente
	 * 
	 */
	public List<Usuario> listarUsuarios() {
		Usuario usuario1 = new Usuario();
		usuario1.setId(1);
		usuario1.setLogin("diego");
		usuario1.setNome("Diego Pessoa");
		usuario1.setSenha("123");
		
		Usuario usuario2 = new Usuario();
		usuario2.setId(2);
		usuario2.setLogin("jose");
		usuario2.setNome("Jose da Silva");
		usuario2.setSenha("321");
		
		return Arrays.asList(usuario1, usuario2);
	}
	
	public Usuario buscarPorId() {
		/** @TODO implementar com acesso a banco. Retornar usuario ao invés de null. **/
		return null;
	}
	
	public void adicionarUsuario(Usuario usuario) {
		/** @TODO implementar com acesso a banco **/
	}
	
	public void removerUsuario(Integer id) {
		/** @TODO implementar com acesso a banco **/
	}
	
	public void atualizarUsuario(Usuario usuario) {
		/** @TODO implementar com acesso a banco **/
	}
	
}
