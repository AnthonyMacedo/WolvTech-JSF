package com.wolvtech.model.repository;

import java.io.Serializable;
import java.util.List;

public interface IRepository<T, L extends Serializable> {

	void gravar(T entity);
	
	void remover(Class<T> classe, L pk);
	
	T buscarPorId(Class<T> classe, L pk);
	
	List<T> buscarTodos(Class<T> classe);
	
	List<T> pesquisar(String palavra);
}
