package br.gov.sp.fatec.projetomaven.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "alu_aluno", schema = "avaliacao")
@PrimaryKeyJoinColumn(name = "alu_id")
public class Aluno extends Usuario {
	
	@Column(name = "alu_ra")
	private Long ra;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "alunos")
	private Set<Trabalho> trabalhos;
	
	public Aluno() {}
	
	public Aluno(String nomeUsuario, String senha, Long ra) {
		this.setNomeUsuario(nomeUsuario);
		this.setSenha(senha);
		this.ra = ra;
	}

	public Long getRa() {
		return ra;
	}

	public void setRa(Long ra) {
		this.ra = ra;
	}

	public Set<Trabalho> getTrabalhos() {
		return trabalhos;
	}

	public void setTrabalhos(Set<Trabalho> trabalhos) {
		this.trabalhos = trabalhos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ra == null) ? 0 : ra.hashCode());
		result = prime * result + ((trabalhos == null) ? 0 : trabalhos.hashCode());
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
		Aluno other = (Aluno) obj;
		if (ra == null) {
			if (other.ra != null)
				return false;
		} else if (!ra.equals(other.ra))
			return false;
		if (trabalhos == null) {
			if (other.trabalhos != null)
				return false;
		} else if (!trabalhos.equals(other.trabalhos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [" + super.toString() + ", ra=" + ra + ", trabalhos=" + trabalhos + "]";
	}
}
