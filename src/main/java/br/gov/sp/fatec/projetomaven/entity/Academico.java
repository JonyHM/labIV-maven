package br.gov.sp.fatec.projetomaven.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "aca_academico")
public class Academico extends Evento {

	@Column(name = "aca_cursos")
	private String cursos;
	
	public Academico() {}
	
	public Academico(String titulo, String local, Date dataHoraAgendamento, String cursos) {
		this.setTitulo(titulo);
		this.setLocal(local);
		this.setDataHoraAgendamento(dataHoraAgendamento);
		this.cursos = cursos;
	}
	
	public String getCursos() {
		return cursos;
	}

	public void setCursos(String cursos) {
		this.cursos = cursos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cursos == null) ? 0 : cursos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Academico other = (Academico) obj;
		if (cursos == null) {
			if (other.cursos != null)
				return false;
		} else if (!cursos.equals(other.cursos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Academico [cursos=" + cursos + "]";
	}
	
}
