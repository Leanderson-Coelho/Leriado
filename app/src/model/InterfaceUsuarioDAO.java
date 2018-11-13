package model;

import java.io.IOException;
import java.util.HashSet;

public interface InterfaceUsuarioDAO extends InterfaceDAO<Usuario>{
	
	boolean validaNovoUsuario(Usuario usuario) ; 
	Usuario autenticaUsuario(String email, String senha);
	Usuario buscar(Integer codigo) throws IOException, ClassNotFoundException;
	Usuario buscarId(Integer id) throws IOException, ClassNotFoundException;
	Usuario buscarEmail(String email) throws IOException, ClassNotFoundException;
	HashSet<Usuario> buscarNome(String nome) throws IOException, ClassNotFoundException;

}
