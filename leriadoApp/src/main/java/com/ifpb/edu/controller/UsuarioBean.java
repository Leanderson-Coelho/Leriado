package com.ifpb.edu.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.validadores.Validator;

@ManagedBean
@RequestScoped
public class UsuarioBean {
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private String dataNasc;
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	@PostConstruct
	public void init() {
		usuarioDao = new UsuarioDaoImpl();
		usuario = new Usuario(); 
	}
	
	public String cadastrar() throws SQLException {
		usuario.setAcesso(1);
		usuario.setAtivo(true);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			usuario.setDatanasc(LocalDate.parse(dataNasc, formatter));			
		}catch(DateTimeParseException e) {
			usuario.setDatanasc(null);
		}
		List<String> msgsErro = Validator.validaTodos(usuario.getNome(), usuario.getSobrenome(), usuario.getEmail(), usuario.getSenha(), usuario.getSexo(), dataNasc, usuario.getTelefone(), usuario.getNumero(), usuario.getCep(), formatter);
		try {
			if(usuarioDao.buscarPorEmail(usuario.getEmail())!=null) {
				msgsErro.add(2, "Email já cadastrado");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			//erro 501
		}
		
		for(String msgErro : msgsErro) {
			if(msgErro!=null) {
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, msgErro));
				System.out.println(msgErro);
				return "login";
			}
		}
		
		usuarioDao.criar(usuario);
		
		return "inicio";
	}
	
	public String atualizarConta() throws SQLException {
		loginBean.getUsuarioLogado().setAcesso(1);
		loginBean.getUsuarioLogado().setAtivo(true);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			loginBean.getUsuarioLogado().setDatanasc(LocalDate.parse(loginBean.getNascimentoToString(), formatter));
			
		}catch(DateTimeParseException e) {
			loginBean.getUsuarioLogado().setDatanasc(null);
		}
		List<String> msgsErro = Validator.validaTodos(loginBean.getUsuarioLogado().getNome(), loginBean.getUsuarioLogado().getSobrenome(), loginBean.getUsuarioLogado().getEmail(), loginBean.getUsuarioLogado().getSenha(), loginBean.getUsuarioLogado().getSexo(), loginBean.getNascimentoToString(), loginBean.getUsuarioLogado().getTelefone(), loginBean.getUsuarioLogado().getNumero(), loginBean.getUsuarioLogado().getCep(), formatter);
		try {
			if(usuarioDao.buscarPorEmail(usuario.getEmail())!=null) {
				msgsErro.add(2, "Email já cadastrado");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			//erro 501
		}
		
		for(String msgErro : msgsErro) {
			if(msgErro!=null) {
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, msgErro));
				System.out.println(msgErro);
				return "atualizarConta";
			}
		}
		
		usuarioDao.atualizar(loginBean.getUsuarioLogado(), loginBean.getUsuarioLogado().getId());
		
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
	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
}
