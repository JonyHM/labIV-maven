package br.gov.sp.fatec.projetomaven.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Desportivo;
import br.gov.sp.fatec.projetomaven.entity.Professor;
import br.gov.sp.fatec.projetomaven.manager.PersistenceManager;

public class DesportivoDaoJpa implements DesportivoDao {
	
	private EntityManager em;
	
	public DesportivoDaoJpa() {
		this(PersistenceManager.getInstance().getEntityManager());
	}
	
	public DesportivoDaoJpa(EntityManager em) {
		this.em = em;
	}	
	
	@Override
	public Desportivo salvarEvento(Desportivo desportivo) {
		try {
			em.getTransaction().begin();
			
			Professor professor = desportivo.getOrganizador();
			
			if(professor != null && professor.getId() == null) {
				ProfessorDao professorDao = new ProfessorDaoJpa(em);
				professorDao.salvarProfessorSemCommit(professor);
			}
			
			salvarEventoSemCommit(desportivo);			
			em.getTransaction().commit();
			
			return desportivo;
		} catch (PersistenceException e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new RuntimeException("Erro ao salvar evento desportivo: \nCausa: " + e);
		}
	}

	@Override
	public void salvarEventoSemCommit(Desportivo desportivo) {
		if(desportivo.getId() == null) {
			em.persist(desportivo);
		} else {
			em.merge(desportivo);
		}
	}

	@Override
	public Desportivo cadastrarEvento(String titulo, String local, Date dataHoraAgendamento, String equipes) {
		Desportivo desportivo = new Desportivo(titulo, local, dataHoraAgendamento, equipes);
		return salvarEvento(desportivo);
	}

	@Override
	public void excluirEventoPorId(Long id) {
		Desportivo desportivo = buscarEventoPorId(id);
		
		if(desportivo == null) {
			throw new RuntimeException("Evento não encontrado");
		}

		em.getTransaction().begin();
		em.remove(desportivo);
		em.getTransaction().commit();
	}

	@Override
	public Desportivo buscarEventoPorId(Long id) {
		String jpql = "SELECT d FROM Desportivo d WHERE d.id = :id";
		TypedQuery<Desportivo> query = em.createQuery(jpql, Desportivo.class);
		query.setParameter("id", id);
		
		return query.getSingleResult();
	}

}
