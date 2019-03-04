package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.dao.publicacao.CurteDAO;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Curte;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class CurteDAOImpDB implements CurteDAO{
	
	private Connection connection;
		
	public CurteDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void cria(Curte curte) throws DataAccessException{
		try {
			String query = "INSERT INTO curte (textoid,usuarioid) "
					+ "VALUES(?,?)";
			PreparedStatement stm =  connection.prepareStatement(query);
			stm.setInt(1,curte.getTexto().getId());
			stm.setInt(2,curte.getUsuario().getId());			
			stm.executeUpdate();
			}catch (Exception e) {
				throw new DataAccessException("Falha ao criar curtida");
			}		
	}
	
	@Override
	public void exclui(Curte curte) throws DataAccessException{
		try {
			String query = "DELETE FROM curte "
					+ "WHERE (textoid=?) AND (usuarioid=?) ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1,curte.getTexto().getId());
			stm.setInt(2, curte.getUsuario().getId());
			stm.executeUpdate();			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao excluir curtida");
		}		
	}

	@Override
	public int quant(Texto texto) throws DataAccessException{
		try {
			String query = "SELECT COUNT(*) FROM curte "
					+ "WHERE textoid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, texto.getId());
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar a quantidade de curtidas.");
		}
		return 0;
	}

	@Override
	public List<Curte> lista(Texto texto) throws DataAccessException{
		List<Curte> cutidas = new ArrayList<Curte>();
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		UsuarioDaoImpl usuarioDAO = new UsuarioDaoImpl();
		Usuario usuario = null;
		int ti = texto.getId();
		try {
			texto = textoDAO.buscar(ti).orElseThrow();
			String query = "SELECT * FROM curte "
					+ "WHERE textoid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, texto.getId());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				usuario = usuarioDAO.buscarPorId(rs.getInt("usuarioid"));
				cutidas.add(new Curte(texto, usuario, rs.getTimestamp("datahora").toLocalDateTime()));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar curtidas.");
		}		
		return cutidas;
	}
	
	

}
