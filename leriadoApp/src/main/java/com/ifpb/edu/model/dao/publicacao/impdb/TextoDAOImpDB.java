package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.dao.publicacao.TextoDAO;
import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.publicacao.Texto;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class TextoDAOImpDB implements TextoDAO {
	
	private Connection connection;	

	public TextoDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public int cria(Texto texto) throws DataAccessException{			
		try{
			String query = "INSERT INTO texto (conteudo,usuarioid) "
					+ "VALUES(?,?) "
					+ "RETURNING id,ativo,datahora";
			PreparedStatement stm = connection.prepareStatement(query);			
			stm.setString(1, texto.getConteudo());			
			stm.setInt(2, texto.getUsuario().getId());
			stm.execute();
			ResultSet rs = stm.getResultSet();
			if(rs.next()) {			
				texto.setId(rs.getInt("id"));
				texto.setAtivo(rs.getBoolean("ativo"));			
				texto.setDatahora(rs.getTimestamp("datahora").toLocalDateTime());
			}
		} catch (Exception e) {			
			throw new DataAccessException("Falha ao criar texto");
		}
		return texto.getId();
	}

	@Override
	public void edita(Texto texto) throws DataAccessException{
		try {
			String query = "UPDATE texto SET "
					+ "ativo=?,conteudo=?,datahora=?,usuarioid=?"
					+ "WHERE id=?";
			PreparedStatement stm = connection.prepareStatement(query);			
			stm.setBoolean(1,texto.getAtivo());
			stm.setString(2, texto.getConteudo());
			stm.setTimestamp(3,java.sql.Timestamp.valueOf(texto.getDatahora()));
			stm.setInt(4, texto.getUsuario().getId());
			stm.setInt(5,texto.getId());
			stm.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();			
			throw new DataAccessException("Falha ao editar texto");
		}
	}

	@Override
	public void exclui(Texto texto) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Texto> buscar(int id) throws DataAccessException {
		Optional<Texto> texto = Optional.empty();
		try {
			UsuarioDaoImpl usuarioDAO = new UsuarioDaoImpl();
			String query = "SELECT * FROM texto "
					+ "WHERE id = ?";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1,id);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				texto = Optional.of(new Texto(
						rs.getInt("id"), 
						rs.getBoolean("ativo"),
						rs.getString("conteudo"),
						rs.getTimestamp("datahora").toLocalDateTime(),
						usuarioDAO.buscarPorId(rs.getInt("usuarioid"))));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao buscar texto");
		} 
		return texto;
	}

	@Override
	public TipoTexto tipo(Texto texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Texto> lista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Texto> lista(int inicio, int quant) {
		// TODO Auto-generated method stub
		return null;
	}

}