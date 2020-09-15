package br.gov.sp.fatec.projetomaven;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.projetomaven.dao.AcademicoDao;
import br.gov.sp.fatec.projetomaven.dao.AcademicoDaoJpa;
import br.gov.sp.fatec.projetomaven.dao.ColetivoDao;
import br.gov.sp.fatec.projetomaven.dao.ColetivoDaoJpa;
import br.gov.sp.fatec.projetomaven.dao.DesportivoDao;
import br.gov.sp.fatec.projetomaven.dao.DesportivoDaoJpa;
import br.gov.sp.fatec.projetomaven.dao.IndividualDao;
import br.gov.sp.fatec.projetomaven.dao.IndividualDaoJpa;
import br.gov.sp.fatec.projetomaven.dao.TrabalhoDao;
import br.gov.sp.fatec.projetomaven.dao.TrabalhoDaoJpa;
import br.gov.sp.fatec.projetomaven.entity.Academico;
import br.gov.sp.fatec.projetomaven.entity.Aluno;
import br.gov.sp.fatec.projetomaven.entity.Coletivo;
import br.gov.sp.fatec.projetomaven.entity.Desportivo;
import br.gov.sp.fatec.projetomaven.entity.Individual;
import br.gov.sp.fatec.projetomaven.entity.Professor;
import br.gov.sp.fatec.projetomaven.entity.Trabalho;
import br.gov.sp.fatec.projetomaven.manager.PersistenceManager;

public class App {
	
    public static void main( String[] args ) {     
    	
    	EntityManager em = PersistenceManager.getInstance().getEntityManager();
    	
    	Aluno aluno = new Aluno("aluno4", "senha", 321654781557L);
    	Professor professor = new Professor("professor4", "senha");
    	
    	Set<Aluno> alunos = new HashSet<Aluno>();
    	alunos.add(aluno);
    	
    	Trabalho trabalho = new Trabalho();
    	trabalho.setDataHoraEntrega(new Date());
    	trabalho.setAlunos(alunos);
    	trabalho.setAvaliador(professor);
    	trabalho.setTitulo("Trabalho Jpa 4");
    	trabalho.setLocalArquivo("D:\\workspace\\projeto-maven\\jpa4.pdf");
    	
    	TrabalhoDao trabalhoDao = new TrabalhoDaoJpa(em);
    	
    	trabalhoDao.salvarTrabalho(trabalho);
    	
    	Trabalho t2 = trabalhoDao.buscarTrabalhoPorNomeAvaliadorETitulo(professor.getNomeUsuario(), 
    			"Trabalho Jpa 4");
    	
    	System.out.println(t2.getLocalArquivo());
    	System.out.println(t2.getDataHoraEntrega());
    	
    	// Entidades novas - Ambiente
    	Individual individual = new Individual(20.0, 3, 1);
    	
    	IndividualDao individualDao = new IndividualDaoJpa(em);
    	individualDao.salvarAmbiente(individual);
    	
    	Coletivo coletivo = new Coletivo(100.0, 2, 6);
    	coletivo.setResponsavel(professor);
    	ColetivoDao coletivoDao = new ColetivoDaoJpa(em);
    	coletivoDao.salvarAmbiente(coletivo);
    	
    	// Entidades novas - Evento
    	Desportivo desportivo = new Desportivo("Campeonato de futebol society Alunos FATEC", 
    			"Av. Cidade Jardim, 414, Jardim Satelite, São José dos Campos - SP", new Date(1584932400000l), 
    			"C.orinthians, Python FC, Javafut, Fé no Py");
    	DesportivoDao desportivoDao = new DesportivoDaoJpa(em);
    	desportivoDao.salvarEvento(desportivo);
    	
    	Academico academico = new Academico("Maratona de Programação", "FATEC Professor Jessen Vidal", 
    			new Date(1584932400000l), "ADS, Banco de Dados");
    	AcademicoDao academicoDao = new AcademicoDaoJpa(em);
    	academicoDao.salvarEvento(academico);
    }
}
