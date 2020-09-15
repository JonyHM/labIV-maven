package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Coletivo;
import br.gov.sp.fatec.projetomaven.entity.Professor;
import br.gov.sp.fatec.projetomaven.manager.PersistenceManager;

public class ColetivoDaoJpa implements ColetivoDao {

	private EntityManager em;
	
	public ColetivoDaoJpa() {
		this(PersistenceManager.getInstance().getEntityManager());
	}
	
	public ColetivoDaoJpa(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Coletivo salvarAmbiente(Coletivo coletivo) {
		try {
			em.getTransaction().begin();
			
			Professor professor = coletivo.getResponsavel();
			
			if(professor != null && professor.getId() == null) {
				ProfessorDao professorDao = new ProfessorDaoJpa(em);
				professorDao.salvarProfessorSemCommit(professor);
			}
			
			salvarAmbienteSemCommit(coletivo);			
			em.getTransaction().commit();
			
			return coletivo;
		} catch (PersistenceException e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new RuntimeException("Erro ao salvar ambiente coletivo: \nCausa: " + e);
		}
	}
	
	@Override
	public void salvarAmbienteSemCommit(Coletivo coletivo) {
		if(coletivo.getId() == null) {
			em.persist(coletivo);
		} else {
			em.merge(coletivo);
		}
	}

	@Override
	public Coletivo cadastrarAmbiente(Double tamanho, int distanciamentoMin, int lotacao) {
		Coletivo coletivo = new Coletivo(tamanho, distanciamentoMin, lotacao);
		return salvarAmbiente(coletivo);
	}

	@Override
	public void excluirAmbientePorId(Long id) {
		Coletivo coletivo = buscarAmbientePorId(id);
		
		if(coletivo == null) {
			throw new RuntimeException("Ambiente não encontrado");
		}

		em.getTransaction().begin();
		em.remove(coletivo);
		em.getTransaction().commit();
	}

	@Override
	public Coletivo buscarAmbientePorId(Long id) {
		String jpql = "SELECT c FROM Coletivo c WHERE c.id = :id";
		TypedQuery<Coletivo> query = em.createQuery(jpql, Coletivo.class);
		query.setParameter("id", id);
		
		return query.getSingleResult();
	}

}
