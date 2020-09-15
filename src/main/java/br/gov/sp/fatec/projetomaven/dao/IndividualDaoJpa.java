package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Individual;
import br.gov.sp.fatec.projetomaven.entity.Professor;
import br.gov.sp.fatec.projetomaven.manager.PersistenceManager;

public class IndividualDaoJpa implements IndividualDao {
	
	private EntityManager em;
	
	public IndividualDaoJpa() {
		this(PersistenceManager.getInstance().getEntityManager());
	}
	
	public IndividualDaoJpa(EntityManager em) {
		this.em = em;
	}	
	
	@Override
	public Individual salvarAmbiente(Individual individual) {
		try {
			em.getTransaction().begin();
			
			Professor professor = individual.getResponsavel();
			if(professor != null && professor.getId() == null) {
				ProfessorDao professorDao = new ProfessorDaoJpa(em);
				professorDao.salvarProfessorSemCommit(professor);
			}
			
			salvarAmbienteSemCommit(individual);			
			em.getTransaction().commit();
			
			return individual;
		} catch (PersistenceException e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new RuntimeException("Erro ao salvar ambiente individual: \nCausa: " + e);
		}
	}

	@Override
	public void salvarAmbienteSemCommit(Individual individual) {
		if(individual.getId() == null) {
			em.persist(individual);
		} else {
			em.merge(individual);
		}
	}

	@Override
	public Individual cadastrarAmbiente(Double tamanho, int distanciamentoMin, int lotacao) {
		Individual individual = new Individual(tamanho, distanciamentoMin, lotacao);
		return salvarAmbiente(individual);
	}

	@Override
	public void excluirAmbientePorId(Long id) {
		Individual individual = buscarAmbientePorId(id);
		
		if(individual == null) {
			throw new RuntimeException("Evento não encontrado");
		}

		em.getTransaction().begin();
		em.remove(individual);
		em.getTransaction().commit();
	}

	@Override
	public Individual buscarAmbientePorId(Long id) {
		String jpql = "SELECT i FROM Individual i WHERE i.id = :id";
		TypedQuery<Individual> query = em.createQuery(jpql, Individual.class);
		query.setParameter("id", id);
		
		return query.getSingleResult();
	}

}
