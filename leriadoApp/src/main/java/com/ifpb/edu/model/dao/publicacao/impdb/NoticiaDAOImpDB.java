package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.NoticiaDAO;
import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.publicacao.Link;
import com.ifpb.edu.model.domain.publicacao.Noticia;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class NoticiaDAOImpDB implements NoticiaDAO {
	
	private Connection connection;

	public NoticiaDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}


	@Override
	public void cria(Noticia noticia) throws DataAccessException {				
		try {
			new PublicacaoDAOImpDB().cria(noticia);			
			String query = "INSERT INTO noticia(publicacaoid, titulo) VALUES (?,?) ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, noticia.getId());
			stm.setString(2, noticia.getTitulo());
			stm.executeUpdate();
			new FotoDAOImpDB().criaFotoNoticia(noticia);
		} catch (Exception e) {
			throw new DataAccessException("Falha ao criar Notícia");
		}
	}	

	@Override
	public void exclui(Noticia noticia) throws DataAccessException {
		try {
			new TextoDAOImpDB().buscar(noticia);			
			if (noticia.getTipoTexto() != TipoTexto.NOTICIA)
				throw new Exception();
			String query = "DELETE FROM texto " + "WHERE id = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, noticia.getId());
			stm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException("Falha ao excluir notícia");
		}
	}

	@Override
	public Noticia buscar(int id) throws DataAccessException {
		Noticia noticia = new Noticia();
		noticia.setId(id);
		buscar(noticia);
		return noticia;
	}

	@Override
	public void buscar(Noticia noticia) throws DataAccessException {
		int id = noticia.getId();
		buscar(id, noticia);
	}

	@Override
	public void buscar(int id, Noticia noticia) throws DataAccessException {
		try {
			String query = "SELECT * FROM noticia "
					+ " WHERE publicacaoid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				noticia.setId(id);
				noticia.setTitulo(rs.getString("titulo"));
				new PublicacaoDAOImpDB().buscar(noticia);
				new FotoDAOImpDB().buscaFotoNoticia(noticia);
			} else
				throw new Exception();
		}catch (Exception e) {
			throw new DataAccessException("Falha ao buscar notícia.");
		}
	}

	@Override
	public int quant() throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM noticia";
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("Falha ao buscar a quantidade de noticias.");
		}
		return 0;
	}

	@Override
	public List<Noticia> lista() throws DataAccessException {
		List<Noticia> noticias = new ArrayList<>();
		try {
			String query = "SELECT * FROM noticia";
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Noticia noticia = new Noticia();
				noticia.setId(rs.getInt("publicacaoid"));
				noticia.setTitulo(rs.getString("titulo"));
				new PublicacaoDAOImpDB().buscar(noticia);
				noticias.add(noticia);				
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar notícias");
		}
		return noticias;
	}

	@Override
	public List<Noticia> lista(int inicio, int quant) throws DataAccessException {
		List<Noticia> noticias = new ArrayList<>();
		try {
			String query = "SELECT * FROM noticia "					
					+ " ORDER BY publicacaoid DESC "
					+ " OFFSET ? LIMIT ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, inicio);
			stm.setInt(2, quant);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Noticia noticia = new Noticia();
				noticia.setId(rs.getInt("publicacaoid"));
				noticia.setTitulo(rs.getString("titulo"));
				new PublicacaoDAOImpDB().buscar(noticia);
				noticias.add(noticia);				
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar notícias");
		}
		return noticias;
	}

}
