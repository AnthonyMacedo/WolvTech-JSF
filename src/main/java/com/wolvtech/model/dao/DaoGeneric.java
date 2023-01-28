package com.wolvtech.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.wolvtech.model.repository.IRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class DaoGeneric<T, L extends Serializable> implements IRepository<T, L> {

	@Inject
	private EntityManager em;

	@Override
	public void gravar(T entity) {
		try {
			em.merge(entity);
		} catch (jakarta.persistence.PersistenceException e) {
			System.out.println("DaoGeneric - Erro ao gravar.");
			e.printStackTrace();
		}
	}

	@Override
	public void remover(Class<T> classe, L pk) {
		try {
			T entity = buscarPorId(classe, pk);
			em.remove(entity);
		} catch (jakarta.persistence.PersistenceException e) {
			System.out.println("DaoGeneric - Erro ao remover cadastro.");
			e.printStackTrace();
		}	
	}

	@Override
	public T buscarPorId(Class<T> classe, L pk) {
		try {
			return em.find(classe, pk);
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscarTodos(Class<T> classe) {
		return em.createQuery("select x from " + classe.getSimpleName() + " x").getResultList();
	}

	@Override
	public List<T> pesquisar(String palavra) {
		return null;
	}

}
