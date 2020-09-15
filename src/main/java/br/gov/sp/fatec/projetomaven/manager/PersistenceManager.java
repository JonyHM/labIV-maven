package br.gov.sp.fatec.projetomaven.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

	private static PersistenceManager instance;
	protected EntityManagerFactory emf;
	
	public PersistenceManager() {}	
	
	public static PersistenceManager getInstance() {
		if(instance == null) {
			instance = new PersistenceManager();
		}
		
		return instance;
	}
	
	public EntityManager getEntityManager() {
		if(emf == null) {
			createEntityManagerFactory();
		}
		
		return emf.createEntityManager();
	}
	
	private void createEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("avaliacao");
	}
}
