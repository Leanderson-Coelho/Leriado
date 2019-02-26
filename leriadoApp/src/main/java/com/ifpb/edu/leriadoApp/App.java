package com.ifpb.edu.leriadoApp;

import java.sql.Date;
import java.time.LocalDate;

import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.domain.Usuario;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UsuarioDaoImpl dao = new UsuarioDaoImpl();
        dao.criar(new Usuario("   ", "", " ", " ", " ", LocalDate.now(), 1, " ", " ", " ", " ",  " ", " "));
        
    }
}
