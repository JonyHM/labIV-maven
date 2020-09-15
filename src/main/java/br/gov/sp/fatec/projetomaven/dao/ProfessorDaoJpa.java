package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Professor;
import br.gov.sp.fatec.projetomaven.exception.UsuarioNaoEncontradoException;
import br.gov.sp.fatec.projetomaven.manager.PersistenceManager;

public class ProfessorDaoJpa implements ProfessorDao {

	private EntityManager em;
	
	public ProfessorDaoJpa() {
		this(PersistenceManager.getInstance().getEntityManager());
	}
	
	public ProfessorDaoJpa(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Professor salvarProfessor(Professor professor) {
		try {
			em.getTransaction().begin();
			
			salvarProfessorSemCommit(professor);
			
			em.getTransaction().commit();
			
			return professor;
		} catch (PersistenceException e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new RuntimeException("Erro ao salvar Professor! \nCausa: " + e);
		}
	}
	

	@Override
	public void salvarProfessorSemCommit(Professor professor) {
		if(professor.getId() == null) {
			em.persist(professor);
		} else {
			em.merge(professor);
		}
	}

	@Override
	public Professor cadastrarProfessor(String nomeUsuario, String senha) {
		Professor professor = new Professor(nomeUsuario, senha);
		return salvarProfessor(professor);
	}

	@Override
	public void excluirProfessorPorNomeUsuario(String nomeUsuario) throws UsuarioNaoEncontradoException {
		Professor professor = buscarProfessorPorNomeUsuario(nomeUsuario);

		if(professor == null) {
			throw new UsuarioNaoEncontradoException("Não foi encontrado Professor com o nome '" + nomeUsuario + "'");
		}
		
		em.getTransaction().begin();
		em.remove(professor);
		em.getTransaction().commit();
	}

	@Override
	public Professor buscarProfessorPorNomeUsuario(String nomeUsuario) {
		String jpql = "SELECT p FROM Professor p WHERE p.nomeUsuario = :nomeUsuario";
		TypedQuery<Professor> query = em.createQuery(jpql, Professor.class);
		query.setParameter("nomeUsuario", nomeUsuario);
		
		return query.getSingleResult();
	}

}
