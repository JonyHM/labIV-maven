package br.gov.sp.fatec.projetomaven.dao;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Aluno;
import br.gov.sp.fatec.projetomaven.entity.Professor;
import br.gov.sp.fatec.projetomaven.entity.Trabalho;
import br.gov.sp.fatec.projetomaven.manager.PersistenceManager;

public class TrabalhoDaoJpa implements TrabalhoDao {

	private EntityManager em;
	
	public TrabalhoDaoJpa() {
		this(PersistenceManager.getInstance().getEntityManager());
	}
	
	public TrabalhoDaoJpa(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Trabalho salvarTrabalho(Trabalho trabalho) {
		try {
			em.getTransaction().begin();
			
			Professor professor = trabalho.getAvaliador();
			Set<Aluno> alunos = trabalho.getAlunos();
			
			if(professor != null && professor.getId() == null) {
				ProfessorDao professorDao = new ProfessorDaoJpa(em);
				professorDao.salvarProfessorSemCommit(professor);
			}
			
			if(alunos != null && !alunos.isEmpty()) {
				AlunoDao alunoDao = new AlunoDaoJpa(em);
				
				for(Aluno aluno : alunos) {
					if(aluno != null && aluno.getId() == null) {
						alunoDao.salvarAlunoSemCommit(aluno);
					}
				}
			}
			
			if(trabalho.getId() == null) {
				em.persist(trabalho);
			} else {
				em.merge(trabalho);
			}
			
			em.getTransaction().commit();
			
			return trabalho;
		} catch (PersistenceException e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new RuntimeException("Erro ao salvar Trabalho! \nCausa: " + e);
		}
	}

	@Override
	public void excluirTrabalhoPorId(Long id) {
		Trabalho trabalho = buscarTrabalhoPorId(id);

		if(trabalho == null) {
			throw new RuntimeException("Não foi encontrado trabalho com o id '" + id + "'");
		}
		
		em.getTransaction().begin();
		em.remove(trabalho);
		em.getTransaction().commit();

	}

	@Override
	public Trabalho buscarTrabalhoPorId(Long id) {
		return em.find(Trabalho.class, id);
	}

	@Override
	public Trabalho buscarTrabalhoPorNomeAvaliadorETitulo(String nomeAvaliador, String titulo) {
		String jpql = "SELECT DISTINCT t FROM Trabalho t INNER JOIN t.avaliador p "
				+ "WHERE t.titulo = :titulo AND p.nomeUsuario = :nomeAvaliador";
		TypedQuery<Trabalho> query = em.createQuery(jpql, Trabalho.class);
		
		query.setParameter("nomeAvaliador", nomeAvaliador);
		query.setParameter("titulo", titulo);
		
		return query.getSingleResult();
	}
}
