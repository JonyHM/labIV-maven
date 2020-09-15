package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Individual;

public interface IndividualDao {

	public Individual salvarAmbiente(Individual individual);
	
	public void salvarAmbienteSemCommit(Individual individual);
	
	public Individual cadastrarAmbiente(Double tamanho, int distanciamentoMin, int lotacao);
	
	public void excluirAmbientePorId(Long id);
	
	public Individual buscarAmbientePorId(Long id);
}
