package com.wolvtech.utils.config.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

		private EntityManagerFactory emf;
		
		public EntityManagerProducer() {
			this.emf = Persistence.createEntityManagerFactory("wolvtech");
		}
		
		@Produces
		@RequestScoped
		public EntityManager createEntityManager() {
			return this.emf.createEntityManager();
		}
		
		public void closeEntityManager(@Disposes EntityManager em) {
			em.close();
		}
	}
