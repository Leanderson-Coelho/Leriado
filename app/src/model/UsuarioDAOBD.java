package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import conecta.Conecta;


public class UsuarioDAOBD implements InterfaceUsuarioDAO{
	
	private final String campoUsuario = "id, ativo, email, senha, nome, sobrenome, "
			+ " sexo, datanasc, acesso, telefone, rua, cidade, "
			+ " estado, numero, cep";
	
	public UsuarioDAOBD() {
			
	}
	
	private Usuario lerUsuario(ResultSet resultSet) throws SQLException {
		if (resultSet==null)
			return null;
		Usuario usuario = new Usuario(
				resultSet.getInt("id"),
				resultSet.getBoolean("ativo"),
				resultSet.getString("email"),
				resultSet.getString("senha"),
				resultSet.getString("nome"),
				resultSet.getString("sobrenome"),
				resultSet.getString("sexo"),
				resultSet.getDate("datanasc").toLocalDate(),
				resultSet.getInt("acesso"),
				resultSet.getString("telefone"),
				resultSet.getString("rua"),
				resultSet.getString("cidade"),
				resultSet.getString("estado"),
				resultSet.getString("numero"),
				resultSet.getString("cep")
		);
		return usuario;
	}
		
	@Override
	public HashSet<Usuario> getLista() throws IOException, ClassNotFoundException {
		HashSet<Usuario> usuarios = new HashSet<>();
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"SELECT " 
					+ campoUsuario
					+ " FROM usuario"
					+ " WHERE ativo"
			);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {			
				usuarios.add(lerUsuario(resultSet));
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
					+ " WHERE ativo AND id=?;"
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
					"INSERT INTO usuario (email,senha,nome,sobrenome,sexo,datanasc,"
					+ " acesso, telefone, rua, cidade, estado, numero, cep) "
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);"
			);
			
			statement.setString(1, obj.getEmail());
			statement.setString(2, obj.getSenha());
			statement.setString(3, obj.getNome());
			statement.setString(4, obj.getSobreNome());
			statement.setString(5, obj.getSexo());
			statement.setDate(6,java.sql.Date.valueOf(obj.getDataNasc()));
			statement.setInt(7, obj.getAcesso());
			statement.setString(8, obj.getTelefone());
			statement.setString(9,obj.getRua());
			statement.setString(10, obj.getCidade());
			statement.setString(11, obj.getEstado());
			statement.setString(12, obj.getNumero());
			statement.setString(13, obj.getCep());
			
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
					+ " SET email=?, senha=?, nome=?, sobrenome=?,"
					+ " sexo=?, datanasc=?, acesso=?, telefone=?, "
					+ " rua=?, cidade=?, estado=?, numero=?, cep=? "
					+ " WHERE id=?"
					
			);
			
			statement.setString(1, obj.getEmail());
			statement.setString(2, obj.getSenha());	
			statement.setString(3, obj.getNome());
			statement.setString(4, obj.getSobreNome());
			statement.setString(5,obj.getSexo());
			statement.setDate(6, java.sql.Date.valueOf(obj.getDataNasc()));
			statement.setInt(7, obj.getAcesso());
			statement.setString(8, obj.getTelefone());
			statement.setString(9,obj.getRua());
			statement.setString(10, obj.getCidade());
			statement.setString(11, obj.getEstado());
			statement.setString(12, obj.getNumero());
			statement.setString(13, obj.getCep());			
			statement.setInt(14, obj.getId());
			
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
					"UPDATE usuario "
					+ " SET ativo=false "					
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
	public Usuario autenticaUsuario(String email, String senha) {
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"SELECT "
					+ campoUsuario
					+ " FROM usuario"
					+ " WHERE ativo AND email=? AND senha=?"
			);
			statement.setString(1, email);
			statement.setString(2, senha);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return lerUsuario(resultSet);
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
					"SELECT "
					+ campoUsuario
					+ " FROM usuario "
					+ "WHERE id=?;"
			);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return lerUsuario(resultSet);
			}
		} catch (ClassNotFoundException | SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
	}
	
	@Override
	public Usuario buscarId(Integer id) throws IOException, ClassNotFoundException{
		return buscar(id);
		
	}

	@Override
	public Usuario buscarEmail(String email) throws IOException, ClassNotFoundException {
		try(Connection connection = Conecta.criarConexao()){
			PreparedStatement statement = connection.prepareStatement(
					"SELECT "
					+ campoUsuario
					+ " FROM usuario "
					+ "WHERE email=?;"
			);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return lerUsuario(resultSet);
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
					"SELECT "
					+ campoUsuario
					+ " FROM usuario "
					+ "WHERE nome ilike ?;"
			);
			statement.setString(1, nome);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {						
				usuarios.add(lerUsuario(resultSet));
			}		
		} catch (ClassNotFoundException | SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return usuarios;
	}	

}
