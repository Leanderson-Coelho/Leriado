package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.PublicacaoDAO;
import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.publicacao.Publicacao;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class PublicacaoDAOImpDB implements PublicacaoDAO {
	
	private Connection connection;
	
	public PublicacaoDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void cria(Publicacao publicacao) throws DataAccessException {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			textoDAO.cria(publicacao);
			String query = "INSERT INTO publicacao (textoid,relevancia) VALUES (?,?)";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, publicacao.getId());
			stm.setInt(2, publicacao.getRelevancia());
			stm.execute();					
		}catch (Exception e) {
			throw new DataAccessException("Falha ao criar publicação");
		}
	}

	@Override
	public void edita(Publicacao publicacao) throws DataAccessException {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			if (textoDAO.tipo(publicacao) != TipoTexto.PUBLICACAO)
				throw new Exception();
			textoDAO.edita(publicacao);
			String query = "UPDATE publicacao SET "
					+ "relevancia = ? "
					+ "WHERE textoid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, publicacao.getRelevancia());
			stm.setInt(2, publicacao.getId());
			stm.execute();
		}catch (Exception e) {
			throw new DataAccessException("Falha ao editar publicação"); 
		}

	}

	@Override
	public void exclui(Publicacao publicacao) throws DataAccessException {
		TextoDAOImpDB textoDAO = new TextoDAOImpDB();
		try {
			if(textoDAO.tipo(publicacao) != TipoTexto.PUBLICACAO)
				throw new Exception();
			textoDAO.exclui(publicacao);			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao excluir publicação");
		}
	}

	@Override
	public int quant() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Publicacao> lista() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publicacao> lista(int inicio, int quant) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
