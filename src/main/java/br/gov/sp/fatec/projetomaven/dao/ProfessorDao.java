package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Professor;
import br.gov.sp.fatec.projetomaven.exception.UsuarioNaoEncontradoException;

public interface ProfessorDao {

	public Professor salvarProfessor(Professor professor);
	
	public void salvarProfessorSemCommit(Professor professor);
	
	public Professor cadastrarProfessor(String nomeUsuario, String senha);
	
	public void excluirProfessorPorNomeUsuario(String nomeUsuario) throws UsuarioNaoEncontradoException;
	
	public Professor buscarProfessorPorNomeUsuario(String nomeUsuario);
}
