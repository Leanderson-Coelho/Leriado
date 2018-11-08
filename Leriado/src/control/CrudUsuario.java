package control;

import java.io.IOException;
import java.util.HashSet;

import model.Usuario;
import model.UsuarioDAO;

/**
 * A classe CrudUsuario representa o CRUD da classe Usuario
 * Composto pelas função de adicionar, buscar, excluir, editar e listar usuários.
 * @author Eduardo Lucas
 * @author Isleimar Oliveira
 * @since 28-08-2018
 * @version 1.0  
 * 
 */


public class CrudUsuario {
	
	private UsuarioDAO usuarioDAO;
	
	/**
	 * Contrutor da classe CrudUsuario
	 * @throws IOException 
	 * 
	 */
	
	public CrudUsuario(){
		try {
			usuarioDAO = new UsuarioDAO();
			return;
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
     * Função que adiciona um novo usuário ao CRUD.
     * @return True para o caso da inclusão seja possível.
     * False para o caso da inclusão não seja possível.
     */
	public boolean adicionaUsusario(Usuario usuario) {
		try {
			usuarioDAO.validaNovoUsuario(usuario);
			return usuarioDAO.salvar(usuario);
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	/**
     * Função que retorna a lista de usuários cadastrados no CRUD.
     * @return HashSet de Usuario.
     */
	public HashSet<Usuario> getUsuarios() {
		try {
			return usuarioDAO.getLista();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setUsuarios(HashSet<Usuario> usuarios) {
		try {
			usuarioDAO.setLista(usuarios);
		}catch (Exception e) {
			e.printStackTrace();
			return;
		}		
	}

	/**
     * Função que realiza uma busca pelo código do usuário.
     * @return o Objeto Usuario cadastrado caso seja localizado.
     * NULL caso o usuário não seja localizado.
     */
	public Usuario buscaUsuario(int codigo){
		try {
			return usuarioDAO.buscar(codigo);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
     * Função que realiza uma busca pelo e-mail do usuário.
     * @return o Objeto Usuario cadastrado caso seja localizado.
     * NULL caso o usuário não seja localizado.
     */
	public Usuario buscaUsuario(String login){
		try {
			return usuarioDAO.buscarLogin(login);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Usuario buscarUsuario(Integer id) {
		try {
			return usuarioDAO.buscarId(id);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
     * Função que substitui os dados dos usuário cadastrado, sendo localizado pelo código.
     * @return TRUE caso o usuário seja localizado e modificado.
     * FALSE caso o usuário não seja localizado.
     */
	public boolean editaUsuario(Usuario usuario){
		try {
			return usuarioDAO.editar(usuario);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
     * Função que exclui um usuário cadastrado.
     * @return TRUE caso a exclusão seja efetuada com sucesso.
     * FALSE caso a exclusão não seja efetuada com sucesso.
     */
	public boolean excluiUsuario(Usuario usuario){
		try {
			return usuarioDAO.deletar(usuario);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Realiza a autenticação requerendo o e-mail e senha do usuário cadastrado
	 * @param email - E-mail do usuário cadastrado
	 * @param senha - Senha do usuário cadastrado
	 * @return O objeto usuario retornado caso a autenticação seja realizada com sucesso.
	 * NULL caso a autenticação não seja realizada com sucesso. 
	 */

	public Usuario autenticaUsuario(String email, String senha) {
		try {
			return usuarioDAO.autenticaUsuario(email, senha);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public String toString() {
		return usuarioDAO.toString();
	}
	
	

}
