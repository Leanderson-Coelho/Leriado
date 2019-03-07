package com.ifpb.edu.view;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.validadores.Validator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        UsuarioDaoImpl dao = new UsuarioDaoImpl();
//        dao.criar(new Usuario("zeCouves@gmail.com", "MaRiA", "Zé", "das Couves", "M", LocalDate.now(), 1, "40028922", "Rua", "Narnia", "Sólido",  "423", "79311-013"));
//        dao.remover(12);
//        System.out.println(dao.login("zeCouves@gmail.com", "MaRia"));
//        Usuario novoUsuario = new Usuario("leanderson@","123","Leanderson","COelho","M",LocalDate.now(),1,"432423","rua","Cz","PB","2","243");
//        dao.atualizar(novoUsuario, 2);
//        System.out.println(dao.buscarPorId(2));
//    	System.out.println(dao.buscarPorEmail(null));
        String n = "    ";
        System.out.println(n==null);
        
        
    }
}
