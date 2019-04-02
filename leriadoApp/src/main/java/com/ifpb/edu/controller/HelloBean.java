package com.ifpb.edu.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="helloBean")
@RequestScoped
public class HelloBean implements Serializable {
	
	private String nome = "EXEMPLO";

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
