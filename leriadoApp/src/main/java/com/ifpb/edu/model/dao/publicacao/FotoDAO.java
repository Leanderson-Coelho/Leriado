package com.ifpb.edu.model.dao.publicacao;

import java.util.List;
import java.util.Optional;

import com.ifpb.edu.model.domain.Usuario;
import com.ifpb.edu.model.domain.publicacao.Foto;
import com.ifpb.edu.model.domain.publicacao.Noticia;
import com.ifpb.edu.model.jdbc.DataAccessException;

public interface FotoDAO {
	String nomeFoto() throws DataAccessException;
	void cria(Foto foto) throws DataAccessException;
	void exclui(Foto foto) throws DataAccessException;
	Foto buscar(int id) throws DataAccessException;
	void buscar(Foto foto) throws DataAccessException;
	void buscar(int id, Foto foto) throws DataAccessException;
	int quant()throws DataAccessException;
	List<Foto> lista() throws DataAccessException;
	List<Foto> lista(int inicio, int quant) throws DataAccessException;
	Foto buscarFotoPerfil(Usuario usuario) throws DataAccessException;
	void criaFotoPerfil(Usuario usuario, Foto foto) throws DataAccessException;
	void mudarFotoPerfil(Usuario usuario, Foto foto) throws DataAccessException;
	void removerFotoPerfil(Usuario usuario, Foto foto) throws DataAccessException;
	void criaFotoNoticia(Noticia noticia) throws DataAccessException;
	void buscaFotoNoticia(Noticia noticia) throws DataAccessException;
	void removerFotoNoticia(Noticia noticia, Foto foto) throws DataAccessException;
}
