package com.wolvtech.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.wolvtech.model.entity.Apartamento;
import com.wolvtech.model.repository.IApartamentoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Named
public class ApartamentoDao extends DaoGeneric<Apartamento, Long> implements IApartamentoRepository {

	@Inject
	private EntityManager em;

	public ApartamentoDao() {
	}

	@SuppressWarnings("unused")
	@Override
	public boolean validarQuartoCadastrado(String numQuarto) {

		Apartamento quartoCadastrado = null;

		try {

			quartoCadastrado = (Apartamento) em
					.createQuery("select a from Apartamento a where a.numQuarto = '" + numQuarto + "'")
					.getSingleResult();

			return true;

		} catch (jakarta.persistence.NoResultException e) {
			System.out.println(e.getMessage());
			System.out.println("QUARTO NAO LOCALIZADO.");
			return false;
		}
	}

	@Override
	public Apartamento verificaSeTemId(String numQuarto) {

		Apartamento quartoCadastrado = (Apartamento) em
				.createQuery("select a from Apartamento a where a.numQuarto = '" + numQuarto + "'").getSingleResult();
		if (quartoCadastrado != null) {
			return quartoCadastrado;
		}
		return null;
	}

	@Override
	public List<Apartamento> pesquisar(String numero) {

		String jpql = "from Apartamento where numQuarto like :numQuarto";

		TypedQuery<Apartamento> query = em.createQuery(jpql, Apartamento.class);

		query.setParameter("numQuarto", numero + "%");

		return query.getResultList();
	}
	
}
