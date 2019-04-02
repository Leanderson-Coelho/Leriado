package com.ifpb.edu.controller;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.domain.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioBean {
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	
	@PostConstruct
	public void init() {
		usuarioDao = new UsuarioDaoImpl();
		usuario = new Usuario(); 
	}
	
	public String cadastrar() throws SQLException {
		usuario.setAcesso(1);
		usuario.setAtivo(true);
		usuarioDao.criar(usuario);
		return "inicio";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
