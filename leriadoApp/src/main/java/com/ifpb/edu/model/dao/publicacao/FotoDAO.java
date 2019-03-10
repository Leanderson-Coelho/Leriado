package com.ifpb.edu.model.dao.publicacao;

import java.util.List;
import java.util.Optional;

import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Foto;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface FotoDAO {
	
	void cria(Foto foto) throws DataAccessException;
	void exclui(Foto foto) throws DataAccessException;
	Foto buscar(int id) throws DataAccessException;
	void buscar(Foto foto) throws DataAccessException;
	void buscar(int id, Foto foto) throws DataAccessException;
	int quant()throws DataAccessException;
	List<Foto> lista() throws DataAccessException;
	List<Foto> lista(int inicio, int quant) throws DataAccessException;
	Optional<Foto> buscarFotoPerfil(Usuario usuario) throws DataAccessException;
	void mudarFotoPerfil(Usuario usuario, Foto foto) throws DataAccessException;
	void removerFotoPerfil(Usuario usuario, Foto foto) throws DataAccessException;
}
