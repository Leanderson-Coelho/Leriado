package com.ifpb.edu.controller;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.domain.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioBean {
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private String teste = "TESTE DE BEAN";
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	@PostConstruct
	public void init() {
		usuarioDao = new UsuarioDaoImpl();
		usuario = new Usuario(); 
	}
	
	public String cadastrar() throws SQLException {
		System.out.println(usuario);
		usuario.setAcesso(1);
		usuario.setAtivo(true);
		usuarioDao.criar(usuario);
		return "inicio";
	}
	
	public String excluirConta() throws SQLException {
		usuarioDao.remover(loginBean.getUsuarioLogado().getId());
		return "login";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
}
