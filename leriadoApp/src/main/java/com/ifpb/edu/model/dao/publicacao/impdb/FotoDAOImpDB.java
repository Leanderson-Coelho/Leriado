package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.ifpb.edu.model.dao.publicacao.FotoDAO;
import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.publicacao.Foto;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class FotoDAOImpDB implements FotoDAO {
	
	private Connection connection;

	public FotoDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}


	@Override
	public void cria(Foto foto) throws DataAccessException {
		new PublicacaoDAOImpDB().cria(foto);
		String query = "INSERT INTO foto(publicacaoid, arquivo) VALUES (?,?)";
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, foto.getId());
			stm.setString(2, foto.getArquivo());
			stm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException("Falha ao criar foto");
		}
	}

	@Override
	public void exclui(Foto foto) throws DataAccessException {
		try {
			new TextoDAOImpDB().buscar(foto);			
			if (foto.getTipoTexto() != TipoTexto.FOTO)
				throw new Exception();
			String query = "DELETE FROM texto " + "WHERE id = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, foto.getId());
			stm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException("Falha ao excluir foto");
		}
	}

	@Override
	public Foto buscar(int id) throws DataAccessException {
		Foto foto = new Foto();
		foto.setId(id);
		buscar(foto);
		return foto;
	}

	@Override
	public void buscar(Foto foto) throws DataAccessException {
		int id = foto.getId();
		buscar(id, foto);
	}

	@Override
	public void buscar(int id, Foto foto) throws DataAccessException {
		try {
			String query = "SELECT * FROM foto "
					+ " WHERE publicacaoid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				foto.setId(id);
				foto.setArquivo(rs.getString("arquivo"));
				new PublicacaoDAOImpDB().buscar(foto);
			} else
				throw new Exception();
		}catch (Exception e) {
			throw new DataAccessException("Falha ao buscar foto");
		}
	}

	@Override
	public int quant() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Foto> lista() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Foto> lista(int inicio, int quant) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
