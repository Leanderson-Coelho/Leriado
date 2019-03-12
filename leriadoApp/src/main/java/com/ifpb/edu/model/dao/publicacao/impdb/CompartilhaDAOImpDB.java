package com.ifpb.edu.model.dao.publicacao.impdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.edu.model.dao.GrupoDaoImpl;
import com.ifpb.edu.model.dao.UsuarioDaoImpl;
import com.ifpb.edu.model.dao.publicacao.CompartilhaDAO;
import com.ifpb.edu.model.dao.publicacao.TipoTexto;
import com.ifpb.edu.model.domain.Grupo;
import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Compartilha;
import com.ifpb.edu.model.domain.publicacao.Publicacao;
import com.ifpb.edu.model.jdbc.ConnectionFactory;
import com.ifpb.edu.model.jdbc.DataAccessException;

public class CompartilhaDAOImpDB implements CompartilhaDAO {
	
	private Connection connection;	

	public CompartilhaDAOImpDB() {
		connection = ConnectionFactory.getInstance().getConnection();
	}
	
	private Compartilha lerTabela(ResultSet rs) throws DataAccessException{
		TipoTexto tipoTexto = null;		
		Publicacao publicacao = null;				
		try {
			switch (rs.getString("tipo")) {		
			case "PUBLICACAO": 
				tipoTexto = TipoTexto.PUBLICACAO;
				publicacao = new PublicacaoDAOImpDB().buscar(rs.getInt("publicacaoid"));
				break;
			case "NOTICIA": 
				tipoTexto = TipoTexto.NOTICIA;
				//publicacao = new NoticiaDAOImpDB().buscar(rs.getInt("publicacaoid"));
				publicacao = new PublicacaoDAOImpDB().buscar(rs.getInt("publicacaoid"));
				break;				
			case "FOTO": 
				tipoTexto = TipoTexto.FOTO;
				//publicacao = new FotoDAOImpDB().buscar(rs.getInt("publicacaoid"));
				publicacao = new PublicacaoDAOImpDB().buscar(rs.getInt("publicacaoid"));
				break;
			case "LINK": 
				tipoTexto = TipoTexto.LINK;
				//publicacao = new LinkDAOImpDB().buscar(rs.getInt("publicacaoid"));
				publicacao = new PublicacaoDAOImpDB().buscar(rs.getInt("publicacaoid"));
				break;
			default: 
				throw new Exception();			
			}			
			Grupo grupo = new GrupoDaoImpl().busca(rs.getInt("grupoid"));
			Usuario usuario = new UsuarioDaoImpl().buscarPorId(rs.getInt("usuarioid"));
			return new Compartilha(LocalDateTime.now(), usuario, publicacao, grupo);
		} catch (Exception e) {
			throw new DataAccessException("Tipo de publicação inválido");
		}
	}

	@Override
	public void cria(Compartilha compartilha) throws DataAccessException {
		try {
		String query = "INSERT INTO compartilha (usuarioid,publicacaoid,grupoid) "
				+ "VALUES (?,?,?) ";
		PreparedStatement stm = connection.prepareStatement(query);
		stm.setInt(1, compartilha.getUsuario().getId());
		stm.setInt(2, compartilha.getPublicacao().getId());
		stm.setInt(3, compartilha.getGrupo().getId());
		stm.execute();
		}catch (Exception e) {
			throw new DataAccessException("Falha ao compartilhar publicação.");	
		}
	}

	@Override
	public void exclui(Compartilha compartilha) throws DataAccessException {
		try {
			String query = "DELETE FROM compartilha "
					+ " WHERE (usuarioid = ?) AND "
					+ " (publicacaoid = ?) AND "
					+ " (grupoid = ?)";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, compartilha.getUsuario().getId());
			stm.setInt(2, compartilha.getPublicacao().getId());
			stm.setInt(3, compartilha.getGrupo().getId());
			stm.execute();
		}catch (Exception e) {
			throw new DataAccessException("Falha ao remover compartilhamento");
		}
	}

	@Override
	public int quant() throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM compartilha";
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar quantidade");
		}
		return 0;
	}

	@Override
	public int quant(Grupo grupo) throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM compartilha "
					+ "WHERE grupoid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, grupo.getId());
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar quantidade");
		}
		return 0;
	}

	@Override
	public int quant(Usuario usuario) throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM compartilha "
					+ "WHERE usuarioid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, usuario.getId());
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar quantidade");
		}
		return 0;
	}

	@Override
	public int quant(Publicacao publicacao) throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM compartilha "
					+ "WHERE publicacaoid = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, publicacao.getId());
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar quantidade");
		}
		return 0;
	}

	@Override
	public List<Compartilha> lista() throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();
		try {
			String query = "SELECT *, tipotexto(publicacaoid) AS tipo FROM compartilha "
					+ " ORDER BY datahora DESC ";
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(lerTabela(rs));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public List<Compartilha> lista(Grupo grupo) throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();		
		try {
			String query = "SELECT *, tipotexto(publicacaoid) AS tipo FROM compartilha "
					+ " WHERE grupoid = ? "
					+ " ORDER BY datahora DESC ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, grupo.getId());
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(lerTabela(rs));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public List<Compartilha> lista(Usuario usuario) throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();		
		try {
			String query = "SELECT *, tipotexto(publicacaoid) AS tipo FROM compartilha "
					+ " WHERE usuarioid = ? "
					+ " ORDER BY datahora DESC ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, usuario.getId());
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(lerTabela(rs));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public List<Compartilha> lista(Publicacao publicacao) throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();
		try {
			String query = "SELECT *, tipotexto(publicacaoid) AS tipo FROM compartilha "
					+ " WHERE publicacaoid = ? "
					+ " ORDER BY datahora DESC ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, publicacao.getId());
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(lerTabela(rs));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public List<Compartilha> lista(int inicio, int quant) throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();		
		try {
			String query = "SELECT *, tipotexto(publicacaoid) AS tipo FROM compartilha"					
					+ " ORDER BY datahora DESC "
					+ " OFFSET ? LIMIT ?";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, inicio);
			stm.setInt(2, quant);
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(lerTabela(rs));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public List<Compartilha> lista(Grupo grupo, int inicio, int quant) throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();
		try {
			String query = "SELECT *, tipotexto(publicacaoid) AS tipo FROM compartilha "
					+ " WHERE grupoid = ? "
					+ " ORDER BY datahora DESC "
					+ " OFFSET ? LIMIT ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, grupo.getId());
			stm.setInt(2, inicio);
			stm.setInt(3, quant);
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(lerTabela(rs));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public List<Compartilha> lista(Usuario usuario, int inicio, int quant) throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();
		try {
			String query = "SELECT *, tipotexto(publicacaoid) AS tipo FROM compartilha "
					+ " WHERE usuarioid = ? "
					+ " ORDER BY datahora DESC "
					+ " OFFSET ? LIMIT ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, usuario.getId());
			stm.setInt(2, inicio);
			stm.setInt(3, quant);
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(lerTabela(rs));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public List<Compartilha> lista(Publicacao publicacao, int inicio, int quant) throws DataAccessException {
		List<Compartilha> comp = new ArrayList<Compartilha>();
		try {
			String query = "SELECT *, tipotexto(publicacaoid) AS tipo FROM compartilha "
					+ " WHERE publicacaoid = ? "
					+ " ORDER BY datahora DESC "
					+ " OFFSET ? LIMIT ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, publicacao.getId());
			stm.setInt(2, inicio);
			stm.setInt(3, quant);
			ResultSet rs =stm.executeQuery();
			while (rs.next()) {
				comp.add(lerTabela(rs));
			}
			
		}catch (Exception e) {
			throw new DataAccessException("Falha ao listar compartilhamento");
		}
		return comp;
	}

	@Override
	public int quantFeed(Usuario usuario) throws DataAccessException {
		try {
			String query = "SELECT COUNT(*) FROM compartilha C WHERE " + 
					"EXISTS (SELECT FROM segue S WHERE (S.seguidoid = C.usuarioid) AND (S.segueid = ?)) OR " + 
					"EXISTS (SELECT FROM participagrupo P WHERE (C.grupoid = P.grupoid) AND (P.usuarioid = ?)) "; 
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, usuario.getId());
			stm.setInt(2, usuario.getId());
			ResultSet rs = stm.executeQuery();
			if(rs.next())
				return rs.getInt(1);
		}catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar a quantidade de publicções no feed");
		}
		return 0;
	}

	@Override
	public List<Compartilha> feed(Usuario usuario, int inicio, int quant) throws DataAccessException {
		List<Compartilha> comps = new ArrayList<Compartilha>();
		try {
			String query = "SELECT * FROM compartilha C WHERE " + 
					"EXISTS (SELECT FROM segue S WHERE (S.seguidoid = C.usuarioid) AND (S.segueid = ?)) OR " + 
					"EXISTS (SELECT FROM participagrupo P WHERE (C.grupoid = P.grupoid) AND (P.usuarioid = ?)) " +
					"ORDER BY C.datahora DESC " +
					"OFFSET ? LIMIT ? "; 
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, usuario.getId());
			stm.setInt(2, usuario.getId());
			stm.setInt(3, inicio);
			stm.setInt(4, quant);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				comps.add(new Compartilha(
						rs.getTimestamp("dataHora").toLocalDateTime(), 
						new UsuarioDaoImpl().buscarPorId(rs.getInt("usuarioid")), 
						new PublicacaoDAOImpDB().buscar(rs.getInt("publicacaoid")), 
						new GrupoDaoImpl().busca(rs.getInt("grupoid"))));
			}
		}catch (Exception e) {
			throw new DataAccessException("Falha ao recuperar o feed");
		}
		return comps;
	}
	
}
