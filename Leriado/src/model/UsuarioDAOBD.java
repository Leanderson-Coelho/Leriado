package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import conecta.Conecta;


public class UsuarioDAOBD implements InterfaceUsuarioDAO{
	
	public UsuarioDAOBD() {
			
	}
		
	@Override
	public HashSet<Usuario> getLista() throws IOException, ClassNotFoundException {
		HashSet<Usuario> usuarios = new HashSet<>();
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"SELECT id, login, senha, nome FROM usuario;"
			);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Usuario usuario = new Usuario(
						resultSet.getInt(1),
						resultSet.getString(2),						
						resultSet.getString(3),
						resultSet.getString(4));						
				usuarios.add(usuario);
			}		
		} catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return usuarios;
	}

	@Override
	public void setLista(HashSet<Usuario> obj) throws IOException, ClassNotFoundException {
		for(Usuario usuario: obj) {
			if(usuarioExiste(usuario)) {
				editar(usuario);
			} else {
				salvar(usuario);
			}
		}
	}
	
	private boolean usuarioExiste(Usuario usuario) {
		if(usuario==null) {
			return false;
		}
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					" SELECT COUNT(*) FROM usuario "
					+ " WHERE id=?;"
			);
			statement.setInt(1, usuario.getId());
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {				
				return (resultSet.getInt(1)!=0);
			}else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean salvar(Usuario obj) throws IOException, ClassNotFoundException {
		if(usuarioExiste(obj))
		return false;	
		
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO usuario (login,senha,nome) "
					+ " VALUES(?,?,?);"
			);
			
			statement.setString(1, obj.getLogin());
			statement.setString(2, obj.getSenha());
			statement.setString(3, obj.getNome());	
			
			return statement.executeUpdate()>0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}			
	}	
	
	@Override
	public Usuario buscar(Object chave) throws IOException, ClassNotFoundException {
		return buscar((Integer)chave);		
	}

	@Override
	public boolean editar(Usuario obj) throws IOException, ClassNotFoundException {
		if(!usuarioExiste(obj))
			return false;
		
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE usuario "
					+ " SET login=?, nome=?, senha=? "
					+ " WHERE id=?"
					
			);
			
			statement.setString(1, obj.getLogin());
			statement.setString(2, obj.getNome());
			statement.setString(3, obj.getSenha());			
			statement.setInt(4, obj.getId());
			
			return statement.executeUpdate()>0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}			
	}

	@Override
	public boolean deletar(Usuario obj) throws IOException, ClassNotFoundException {

		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"DELETE FROM usuario "					
					+ " WHERE id=?"
					
			);		
			
			statement.setInt(1, obj.getId());
			
			return statement.executeUpdate()>0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}			
	}

	@Override
	public boolean validaNovoUsuario(Usuario usuario) {		
		return !usuarioExiste(usuario) ;
	}

	@Override
	public Usuario autenticaUsuario(String login, String senha) {
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"SELECT id, nome"
					+ " FROM usuario "
					+ "WHERE login=? AND senha=?;"
			);
			statement.setString(1, login);
			statement.setString(2, senha);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				Usuario usuario = new Usuario(
						resultSet.getInt(1),
						login,
						senha,
						resultSet.getString(2));
				return usuario;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
	}

	@Override
	public Usuario buscar(Integer id) throws IOException, ClassNotFoundException {
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"SELECT   login, senha, nome"
					+ " FROM usuario "
					+ "WHERE id=?;"
			);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				Usuario usuario = new Usuario(
						id,
						resultSet.getString(1),						
						resultSet.getString(2),
						resultSet.getString(3));
				return usuario;
			}
		} catch (ClassNotFoundException | SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
	}
	
	@Override
	public Usuario buscarId(Integer id) throws IOException, ClassNotFoundException{
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"SELECT id, login, senha, nome"
					+ " FROM usuario "
					+ "WHERE id=?;"
			);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				Usuario usuario = new Usuario(
						resultSet.getInt(1),
						resultSet.getString(2),						
						resultSet.getString(3),
						resultSet.getString(4));
				return usuario;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
		
	}

	@Override
	public Usuario buscarLogin(String login) throws IOException, ClassNotFoundException {
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"SELECT id, login, senha, nome"
					+ " FROM usuario "
					+ "WHERE login=?;"
			);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				Usuario usuario = new Usuario(
						resultSet.getInt(1),
						resultSet.getString(2),						
						resultSet.getString(3),
						resultSet.getString(4));
				return usuario;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
	}

	@Override
	public HashSet<Usuario> buscarNome(String nome) throws IOException, ClassNotFoundException {
		HashSet<Usuario> usuarios = new HashSet<>();
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"SELECT id, login, senha, nome"
					+ " FROM usuario "
					+ "WHERE nome ilike ?;"
			);
			statement.setString(1, nome);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Usuario usuario = new Usuario(
						resultSet.getInt(1),
						resultSet.getString(2),						
						resultSet.getString(3),
						resultSet.getString(4));						
				usuarios.add(usuario);
			}		
		} catch (ClassNotFoundException | SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return usuarios;
	}	

}
