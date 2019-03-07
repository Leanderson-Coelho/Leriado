package com.ifpb.edu.validadores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ifpb.edu.model.dao.UsuarioDao;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;

public class Validator {
	
	public static List<String> validaTodos(String nome, String sobrenome, String email, String senha, String sexo, String dataNasc,
			String telefone, String numero, String cep, DateTimeFormatter formatter) {
		List<String> l = new ArrayList<>(); 
		l.add(nome(nome));
		l.add(sobrenome(sobrenome));
		l.add(email(email));
		l.add(senha(senha));
		l.add(sexo(sexo));
		l.add(dataNasc(dataNasc, formatter));
		l.add(telefone(telefone));
		l.add(numero(numero));
		l.add(cep(cep));
		return l;
	}

	public static String email(String email) {
		if(email.length()>100) {
			return "Sequência de caracteres muito grande";
		}
		if(email.isEmpty() || email.equals(null)) {
			return "Preencha os campos obrigatórios";
		}
		Pattern p = Pattern.compile("^[\\w\\.\\-\\+]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
		if(!p.matcher(email).matches()) {
			return "Formatação inválida";
		}
		return null;
	}
	public static String senha(String senha) {
		if(senha.length()>50) {
			return "Sequência de caracteres muito grande";
		}
		if(senha.isEmpty() || senha.equals(null)) {
			return "Preencha os campos obrigatórios";
		}
		if(senha.length()<8) {
			return "A senha deve conter no mínimo 8 dígitos";
		}
		return null;
	}
	
	public static String nome(String nome) {
		if(nome.length()>50) {
			return "Sequência de caracteres muito grande";
		}
		if(nome.isEmpty() || nome.equals(null)) {
			return "Preencha os compos obrigatórios";
		}
		if(nome.length()<2) {
			return "Nome deve conter mais de uma letra";
		}
		Pattern p = Pattern.compile("^[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);
		if(!p.matcher(nome).matches()) {
			return "Nome inválido";
		}
		return null;
	}
	
	public static String sobrenome(String sobrenome) {
		if(sobrenome.length()>100) {
			return "Sequência de caracteres muito grande";
		}
		if(sobrenome.isEmpty() || sobrenome.equals(null)) {
			return "Preencha os compos obrigatórios";
		}
		if(sobrenome.length()<2) {
			return "Nome deve conter mais de uma letra";
		}
		Pattern p = Pattern.compile("^[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);
		if(!p.matcher(sobrenome).matches()) {
			return "Nome inválido";
		}
		return null;
	}
	
	public static String sexo(String sexo) {
		if(sexo.isEmpty() && sexo.equals(null)) {
			return "Informe o seu sexo";
		}
		if(!sexo.equals("M") && !sexo.equals("F")) {
			return "Valores inválidos";
		}
		return null;
	}
	public static String dataNasc(String data, DateTimeFormatter formatter) {
		
		try {
			LocalDate d = LocalDate.parse(data, formatter);
			if(d.isAfter(LocalDate.now()) || d.isBefore(LocalDate.now().minusYears(110L))){
				return "Você não pode ter esta idade :)";
			}			
		}catch(DateTimeParseException e) {
			return "Formato de data inválida";
		}
		return null;
	}
	
	public static String telefone(String telefone) {
		if(!telefone.isEmpty() && !telefone.equals(null)) {
			
			Pattern p = Pattern.compile("^\\(\\d{2}\\) \\d{4}\\d?-\\d{4}$", Pattern.CASE_INSENSITIVE);
			if(!p.matcher(telefone).matches()) {
				return "Preencha o campo corretamente com seu número de telefone";
			}
			
		}
		
		return null;
	}
	
	public static String cep(String cep) {
		Pattern p = Pattern.compile("\\d{5}-\\d{3}", Pattern.CASE_INSENSITIVE);
		if(!cep.isEmpty() && !cep.equals(null)) {
			
			if(!p.matcher(cep).matches()) {
				return "Preencha o campo corretamente com o número de CEP";
			}
			
		}
		
		return null;
	}
	
	public static String numero(String numero) {
		Pattern p = Pattern.compile("\\d+", Pattern.CASE_INSENSITIVE);
		if(!numero.isEmpty() && !numero.equals(null)) {
			
			if(!p.matcher(numero).matches()) {
				return "Preencha o campo corretamente com o número da sua residência";
			}
			
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
