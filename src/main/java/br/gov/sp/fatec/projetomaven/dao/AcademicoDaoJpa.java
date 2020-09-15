package br.gov.sp.fatec.projetomaven.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Academico;
import br.gov.sp.fatec.projetomaven.entity.Professor;
import br.gov.sp.fatec.projetomaven.manager.PersistenceManager;

public class AcademicoDaoJpa implements AcademicoDao {

	private EntityManager em;
	
	public AcademicoDaoJpa() {
		this(PersistenceManager.getInstance().getEntityManager());
	}
	
	public AcademicoDaoJpa(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Academico salvarEvento(Academico academico) {
		try {
			em.getTransaction().begin();
			
			Professor professor = academico.getOrganizador();
			
			if(professor != null && professor.getId() == null) {
				ProfessorDao professorDao = new ProfessorDaoJpa(em);
				professorDao.salvarProfessorSemCommit(professor);
			}
			
			salvarEventoSemCommit(academico);			
			em.getTransaction().commit();
			
			return academico;
		} catch (PersistenceException e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new RuntimeException("Erro ao salvar evento academico: \nCausa: " + e);
		}
	}
	
	@Override
	public void salvarEventoSemCommit(Academico academico) {
		if(academico.getId() == null) {
			em.persist(academico);
		} else {
			em.merge(academico);
		}
	}

	@Override
	public Academico cadastrarEvento(String titulo, String local, Date dataHoraAgendamento, String cursos) {
		Academico academico = new Academico(titulo, local, dataHoraAgendamento, cursos);
		
		return salvarEvento(academico);
	}

	@Override
	public void excluirEventoPorId(Long id) {
		Academico academico = buscarEventoPorId(id);

		if(academico == null) {
			throw new RuntimeException("Evento não encontrado");
		}

		em.getTransaction().begin();
		em.remove(academico);
		em.getTransaction().commit();
	}

	@Override
	public Academico buscarEventoPorId(Long id) {
		String jpql = "SELECT a FROM Academico a WHERE a.id = :id";
		TypedQuery<Academico> query = em.createQuery(jpql, Academico.class);
		query.setParameter("id", id);
		
		return query.getSingleResult();
	}

}
