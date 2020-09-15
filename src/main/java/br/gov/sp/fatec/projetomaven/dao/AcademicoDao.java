package br.gov.sp.fatec.projetomaven.dao;

import java.util.Date;

import br.gov.sp.fatec.projetomaven.entity.Academico;

public interface AcademicoDao {

	public Academico salvarEvento(Academico academico);
	
	public void salvarEventoSemCommit(Academico academico);
	
	public Academico cadastrarEvento(String titulo, String local, Date dataHoraAgendamento, String cursos);
	
	public void excluirEventoPorId(Long id);
	
	public Academico buscarEventoPorId(Long id);
}
