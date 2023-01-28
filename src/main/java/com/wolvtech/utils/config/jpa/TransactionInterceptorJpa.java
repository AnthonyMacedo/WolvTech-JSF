package com.wolvtech.utils.config.jpa;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.wolvtech.utils.annotations.TransactionJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Interceptor
@TransactionJpa
@Priority(Interceptor.Priority.APPLICATION) //Interceptador abrir e fechar a transação
public class TransactionInterceptorJpa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction transaction = em.getTransaction();
		boolean criador = false;

		try {
			if (!transaction.isActive()) {
				// truque para fazer rollback no que já passou
				// (senão, um futuro commit confirmaria até mesmo operações sem transação)
				transaction.begin();
				transaction.rollback();

				// agora sim inicia a transação
				transaction.begin();

				criador = true;
			}

			return context.proceed();
		} catch (Exception e) {
			if (transaction != null && criador) {
				transaction.rollback();
			}

			throw e;
		} finally {
			if (transaction != null && transaction.isActive() && criador) {
				transaction.commit();
			}
		}
	}
}
