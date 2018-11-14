package model;

import java.time.LocalDate;

/** 
 * Entidade de negócio responsável por manter dados do usuário
 *
 */
public class Usuario {

	private Integer id;	
	private Boolean ativo;
	private String email;	
	private String senha;	
	private String nome;
	private String sobreNome;
	private String sexo;
	private String dataNasc;
	private Integer acesso;
	private String telefone;
	private String rua;
	private String cidade;
	private String estado;
	private String numero;
	private String cep;
	
	
	public Usuario() {
	}
	
	

	public Usuario(Integer id, Boolean ativo, String email, String senha, String nome, String sobreNome, String sexo,
			String dataNasc, Integer acesso, String telefone, String rua, String cidade, String estado,
			String numero, String cep) {
		super();
		this.id = id;
		this.ativo = ativo;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.sexo = sexo;
		this.dataNasc = dataNasc;
		this.acesso = acesso;
		this.telefone = telefone;
		this.rua = rua;
		this.cidade = cidade;
		this.estado = estado;
		this.numero = numero;
		this.cep = cep;
	}



	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}



	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Integer getAcesso() {
		return acesso;
	}

	public void setAcesso(Integer acesso) {
		this.acesso = acesso;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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



	@Override
	public String toString() {
		return "Usuario [id=" + id + ", ativo=" + ativo + ", email=" + email + ", senha=" + senha + ", nome=" + nome
				+ ", sobreNome=" + sobreNome + ", sexo=" + sexo + ", dataNasc=" + dataNasc + ", acesso=" + acesso
				+ ", telefone=" + telefone + ", rua=" + rua + ", cidade=" + cidade + ", estado=" + estado + ", numero="
				+ numero + ", cep=" + cep + "]";
	}
	
}
