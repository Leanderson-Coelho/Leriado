package conecta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conecta {
	static final String URL = "jdbc:postgresql://localhost:5432/ProjetoPW1";
	static final String USER = "postgres";
	static final String PASS = "123456";
	
	public static Connection criarConexao() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		Connection conecta = DriverManager.getConnection(URL, USER, PASS);
		if (conecta != null) {
			System.out.print(".");
			return conecta;
		}
		return null;				
	}

}
