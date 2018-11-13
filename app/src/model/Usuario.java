package model;

/** 
 * Entidade de negócio responsável por manter dados do usuário
 *
 */
public class Usuario {

	private Integer id;	
	private String login;	
	private String senha;	
	private String nome;
	
	public Usuario(int id, String login, String senha, String nome) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
