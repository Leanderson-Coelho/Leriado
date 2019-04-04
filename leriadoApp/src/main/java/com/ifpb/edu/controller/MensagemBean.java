package com.ifpb.edu.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.ifpb.edu.model.dao.MensagemDaoImpl;
import com.ifpb.edu.model.domain.Mensagem;


@ManagedBean
@RequestScoped
public class MensagemBean {
	MensagemDaoImpl mensagemDao;
	String msg;
	List<Mensagem> msgs;
	
	@PostConstruct
	public void init() {
		mensagemDao = new MensagemDaoImpl();
		msgs = new ArrayList<>();
	}
	
	public String criarMensagem() {
		mensagemDao.criar(new Mensagem("1", "2", LocalDate.now(), msg));
		listarMensagens();
		return "";
	}
	
	public void listarMensagens() {
		msgs = mensagemDao.recuperarMensagens();
//		System.out.println(msgs);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Mensagem> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<Mensagem> msgs) {
		this.msgs = msgs;
	}
	
	
}
