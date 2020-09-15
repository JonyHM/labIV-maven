package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Aluno;
import br.gov.sp.fatec.projetomaven.exception.UsuarioNaoEncontradoException;

public interface AlunoDao {

	public Aluno salvarAluno(Aluno aluno);
	
	public void salvarAlunoSemCommit(Aluno aluno);
	
	public Aluno cadastrarAluno(String nomeUsuario, String senha, Long ra);
	
	public void excluirAlunoPorRa(String ra) throws UsuarioNaoEncontradoException;
	
	public Aluno buscarAlunoPorRa(String ra);
	
	public Aluno buscarAlunoPorRaENome(String ra, String nomeUsuario);
}
