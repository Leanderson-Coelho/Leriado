package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.NoticiaDAO;
import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.publicacao.Foto;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buscar(Noticia noticia) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void buscar(int id, Noticia noticia) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public int quant() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Noticia> lista() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Noticia> lista(int inicio, int quant) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
