package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Trabalho;

public interface TrabalhoDao {

	public Trabalho salvarTrabalho(Trabalho trabalho);
	
	public void excluirTrabalhoPorId(Long id);
	
	public Trabalho buscarTrabalhoPorId(Long id);
	
	public Trabalho buscarTrabalhoPorNomeAvaliadorETitulo(String nomeAvaliador, String titulo);
}
