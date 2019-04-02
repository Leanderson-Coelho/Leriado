package com.ifpb.edu.controller;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.domain.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean {
	private UsuarioDao usuarioDao;
	
	private Usuario usuarioLogado;
	
	private String login;
	private String senha;
	
	@PostConstruct
	public void init() {
		usuarioDao = new UsuarioDaoImpl();
	}
	
	public String verificacaoLogin() throws Exception {
		if(usuarioDao.login(login, senha)) {
			usuarioLogado = usuarioDao.buscarPorEmail(login);
			return "inicio";
		}else {
			return "login";
		}
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
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
	
	
}
