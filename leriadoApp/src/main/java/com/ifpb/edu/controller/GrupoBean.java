package com.ifpb.edu.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.ifpb.edu.model.dao.GrupoDao;
import com.ifpb.edu.model.dao.GrupoDaoImpl;
import com.ifpb.edu.model.domain.Grupo;
import com.sun.faces.facelets.util.Path;

@ManagedBean
@RequestScoped
public class GrupoBean {

	private GrupoDao grupoDao;
	private Grupo grupo;
	private Part foto;
	private List<Grupo> grupos;
	
	@PostConstruct
	public void init() {
		grupoDao = new GrupoDaoImpl();
		grupo = new Grupo();
		grupos = grupoDao.listar();
	}
	
	public void cadastrarGrupo() throws IOException, SQLException {
		String nome = LocalTime.now()+"-"+foto.getSubmittedFileName();
		try(InputStream file = foto.getInputStream()){
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String pathReal = request.getServletContext().getRealPath("");
			System.out.println(pathReal+nome);
			Files.copy(file, new File(pathReal +"userimg/"+ nome).toPath(), StandardCopyOption.REPLACE_EXISTING );
		}
		grupo.setFoto(nome);
		grupo.setDataHora(LocalDateTime.now());
		grupo.setAtivo(true);
		grupoDao.criar(grupo);
		System.out.println(grupo);
		
	}
	
	public String listar() {
		grupos = grupoDao.listar();
		return "listarGrupos";
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Part getFoto() {
		return foto;
	}

	public void setFoto(Part foto) {
		this.foto = foto;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	
	
}
