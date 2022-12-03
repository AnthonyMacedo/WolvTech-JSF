package com.wolvtech.model.dao;

import javax.inject.Inject;
import javax.inject.Named;

import com.wolvtech.model.entity.Funcionario;
import com.wolvtech.model.repository.IFuncionarioRepository;

import jakarta.persistence.EntityManager;

@Named
public class FuncionarioDao extends DaoGeneric<Funcionario, Long> implements IFuncionarioRepository {

	@Inject
	private EntityManager em;

	public FuncionarioDao() {
	}

	@Override
	public Funcionario consultarUsuario(String usuario, String senha) {

		Funcionario funcionario = null;

		try {

			funcionario = (Funcionario) em.createQuery(
					"select f from Funcionario f where f.usuario = '" + usuario + "' and f.senha = '" + senha + "'")
					.getSingleResult();

		} catch (jakarta.persistence.NoResultException e) {
			System.out.println(e + " / Usuário ou senha incorretos. - FUNCIONARIO");
		}
		return funcionario;
	}

}
