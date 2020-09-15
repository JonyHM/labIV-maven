package br.gov.sp.fatec.projetomaven.dao;

import java.util.Date;

import br.gov.sp.fatec.projetomaven.entity.Desportivo;

public interface DesportivoDao {

	public Desportivo salvarEvento(Desportivo desportivo);
	
	public void salvarEventoSemCommit(Desportivo desportivo);
	
	public Desportivo cadastrarEvento(String titulo, String local, Date dataHoraAgendamento, String equipes);
	
	public void excluirEventoPorId(Long id);
	
	public Desportivo buscarEventoPorId(Long id);
}
