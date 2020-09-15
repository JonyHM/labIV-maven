package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Aluno;
import br.gov.sp.fatec.projetomaven.exception.UsuarioNaoEncontradoException;
import br.gov.sp.fatec.projetomaven.manager.PersistenceManager;

public class AlunoDaoJpa implements AlunoDao {
	
	private EntityManager em;
	
	public AlunoDaoJpa() {
		this(PersistenceManager.getInstance().getEntityManager());
	}
	
	public AlunoDaoJpa(EntityManager em) {
		this.em = em;
	}

	@Override
	public Aluno salvarAluno(Aluno aluno) {
		try {
			em.getTransaction().begin();
			salvarAlunoSemCommit(aluno);			
			em.getTransaction().commit();
			
			return aluno;
		} catch (PersistenceException e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new RuntimeException("Erro ao salvar aluno: \nCausa: " + e);
		}
	}
	
	@Override
	public void salvarAlunoSemCommit(Aluno aluno) {
		if(aluno.getId() == null) {				
			em.persist(aluno);
		} else {
			em.merge(aluno);
		}
	}

	@Override
	public Aluno cadastrarAluno(String nomeUsuario, String senha, Long ra) {
		Aluno aluno = new Aluno(nomeUsuario, senha, ra);
		
		return salvarAluno(aluno);
	}

	@Override
	public void excluirAlunoPorRa(String ra) throws UsuarioNaoEncontradoException {
		Aluno aluno = buscarAlunoPorRa(ra);
		
		if(aluno == null) {
			throw new UsuarioNaoEncontradoException("Aluno não encontrado");
		}

		em.getTransaction().begin();
		em.remove(aluno);
		em.getTransaction().commit();
	}

	@Override
	public Aluno buscarAlunoPorRa(String ra) {
		String jpql = "SELECT a FROM Aluno a WHERE a.ra = :ra";
		TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
		query.setParameter("ra", ra);
		
		return query.getSingleResult();
	}

	@Override
	public Aluno buscarAlunoPorRaENome(String ra, String nomeUsuario) {
		// TODO Bolar uma query com campos melhores, pois essa não faz sentido
		return null;
	}

}
