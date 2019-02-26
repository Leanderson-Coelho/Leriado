package com.ifpb.edu.model.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Usuario {
	private String email;
	private String senha;
	private String nome;
	private String sobrenome;
	private String sexo;
	private LocalDate datanasc;
	private Integer acesso;
	private String telefone;
	private String rua;
	private String cidade;
	private String estado; 
	private String numero; 
	private String cep;
	public Usuario(String email, String senha, String nome, String sobrenome, String sexo, LocalDate datanasc,
			Integer acesso, String telefone, String rua, String cidade, String estado, String numero, String cep) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.datanasc = datanasc;
		this.acesso = acesso;
		this.telefone = telefone;
		this.rua = rua;
		this.cidade = cidade;
		this.estado = estado;
		this.numero = numero;
		this.cep = cep;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public LocalDate getDatanasc() {
		return datanasc;
	}
	public void setDatanasc(LocalDate datanasc) {
		this.datanasc = datanasc;
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
	@Override
	public String toString() {
		return "Usuario [email=" + email + ", senha=" + senha + ", nome=" + nome + ", sobrenome=" + sobrenome
				+ ", sexo=" + sexo + ", datanasc=" + datanasc + ", acesso=" + acesso + ", telefone=" + telefone
				+ ", rua=" + rua + ", cidade=" + cidade + ", estado=" + estado + ", numero=" + numero + ", cep=" + cep
				+ "]";
	}
	
}
