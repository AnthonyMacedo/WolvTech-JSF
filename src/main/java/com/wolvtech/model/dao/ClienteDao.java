package com.wolvtech.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.wolvtech.model.entity.Cliente;
import com.wolvtech.model.repository.IClienteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Named
public class ClienteDao extends DaoGeneric<Cliente, Long> implements IClienteRepository {

	@Inject
	private EntityManager em;

	public ClienteDao() {
	}
	
	@Override
	public Cliente verificaCadastroCliente(String cpf) {

		Cliente cliente = null;

		try {

			cliente = (Cliente) em.createQuery("select c from Cliente c where c.cpf = '" + cpf + "'")
					.getSingleResult();
			
			if(cliente != null) {
				return cliente;
			}
			
		} catch (jakarta.persistence.NoResultException e) {
			System.out.println("CPF NÃO LOCALIZADO/CADASTRADO.");
			e.getMessage();
		}
		return null;
	}
	
	@Override
	public List<Cliente> pesquisar(String nome) {
		String jpql = "from Cliente where nome like :nome";
		
		TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
		
		query.setParameter("nome", nome + "%");
		
		return query.getResultList();
	}
}
