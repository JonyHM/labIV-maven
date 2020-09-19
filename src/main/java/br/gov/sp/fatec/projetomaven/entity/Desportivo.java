package br.gov.sp.fatec.projetomaven.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "des_desportivo")
@PrimaryKeyJoinColumn(name = "des_id")
public class Desportivo extends Evento {

	@Column(name = "des_equipes")
	private String equipes;
	
	public Desportivo() {}
	
	public Desportivo(String titulo, String local, Date dataHoraAgendamento, String equipes) {
		this.setTitulo(titulo);
		this.setLocal(local);
		this.setDataHoraAgendamento(dataHoraAgendamento);
		this.equipes = equipes;
	}

	public String getEquipes() {
		return equipes;
	}

	public void setEquipes(String equipes) {
		this.equipes = equipes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((equipes == null) ? 0 : equipes.hashCode());
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
		Desportivo other = (Desportivo) obj;
		if (equipes == null) {
			if (other.equipes != null)
				return false;
		} else if (!equipes.equals(other.equipes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Desportivo [equipes=" + equipes + "]";
	}
	
}
