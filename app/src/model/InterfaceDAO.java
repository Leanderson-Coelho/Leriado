package model;


import java.io.IOException;
import java.util.HashSet;

public interface InterfaceDAO<T>{
	HashSet<T> getLista()throws IOException, ClassNotFoundException;	
	void setLista(HashSet<T> obj) throws IOException, ClassNotFoundException;	
	boolean salvar(T obj) throws IOException, ClassNotFoundException;
	T buscar(Object chave) throws IOException, ClassNotFoundException;
	boolean editar(T obj) throws IOException, ClassNotFoundException;
	boolean deletar(T obj) throws IOException, ClassNotFoundException;

}
