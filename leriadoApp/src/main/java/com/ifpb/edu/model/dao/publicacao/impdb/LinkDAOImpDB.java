package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.LinkDAO;
import com.ifpb.edu.model.domain.publicacao.Link;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class LinkDAOImpDB implements LinkDAO {
	private Connection connection;
	
	public LinkDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void cria(Link link) throws DataAccessException {		
		new PublicacaoDAOImpDB().cria(link);
		String query = "INSERT INTO link(publicacaoid, link) VALUES (?,?)";
		try {
		PreparedStatement stm = connection.prepareStatement(query);
		stm.setInt(1, link.getId());
		stm.setString(2, link.getLink());
		stm.executeUpdate();
		}catch (Exception e) {
			throw new DataAccessException("Falha ao criar link");
		}
	}

	@Override
	public void exclui(Link link) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public int quant() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Link> lista() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
