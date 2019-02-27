package com.ifpb.edu.leriadoApp;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.domain.Usuario;

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
        dao.remover(12);
        System.out.println(dao.login("zeCouves@gmail.com", "MaRia"));
    }
}
