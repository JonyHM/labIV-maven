package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Coletivo;

public interface ColetivoDao {

	public Coletivo salvarAmbiente(Coletivo coletivo);
	
	public void salvarAmbienteSemCommit(Coletivo coletivo);
	
	public Coletivo cadastrarAmbiente(Double tamanho, int distanciamentoMin, int lotacao);
	
	public void excluirAmbientePorId(Long id);
	
	public Coletivo buscarAmbientePorId(Long id);
}
