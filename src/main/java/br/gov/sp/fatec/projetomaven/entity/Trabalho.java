package br.gov.sp.fatec.projetomaven.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.gov.sp.fatec.projetomaven.entity.common.AutoId;

@Entity
@Table(name = "tra_trabalho", schema = "avaliacao")
@PrimaryKeyJoinColumn(name = "tra_id")
public class Trabalho extends AutoId {
	
	@Column(name = "tra_titulo")
	private String titulo;
	
	@Column(name = "tra_data_hora_entrega")
	private Date dataHoraEntrega;
	
	@Column(name = "tra_local_arquivo")
	private String localArquivo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pro_avaliador_id")
	private Professor avaliador;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ent_entrega", 
		joinColumns = { @JoinColumn(name = "tra_id") },
		inverseJoinColumns = { @JoinColumn(name = "alu_id") })
	private Set<Aluno> alunos;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataHoraEntrega() {
		return dataHoraEntrega;
	}

	public void setDataHoraEntrega(Date dataHoraEntrega) {
		this.dataHoraEntrega = dataHoraEntrega;
	}

	public String getLocalArquivo() {
		return localArquivo;
	}

	public void setLocalArquivo(String localArquivo) {
		this.localArquivo = localArquivo;
	}

	public Professor getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Professor avaliador) {
		this.avaliador = avaliador;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((alunos == null) ? 0 : alunos.hashCode());
		result = prime * result + ((avaliador == null) ? 0 : avaliador.hashCode());
		result = prime * result + ((dataHoraEntrega == null) ? 0 : dataHoraEntrega.hashCode());
		result = prime * result + ((localArquivo == null) ? 0 : localArquivo.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Trabalho other = (Trabalho) obj;
		if (alunos == null) {
			if (other.alunos != null)
				return false;
		} else if (!alunos.equals(other.alunos))
			return false;
		if (avaliador == null) {
			if (other.avaliador != null)
				return false;
		} else if (!avaliador.equals(other.avaliador))
			return false;
		if (dataHoraEntrega == null) {
			if (other.dataHoraEntrega != null)
				return false;
		} else if (!dataHoraEntrega.equals(other.dataHoraEntrega))
			return false;
		if (localArquivo == null) {
			if (other.localArquivo != null)
				return false;
		} else if (!localArquivo.equals(other.localArquivo))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trabalho [titulo=" + titulo + ", dataHoraEntrega=" + dataHoraEntrega + ", localArquivo=" + localArquivo
				+ ", avaliador=" + avaliador + ", alunos=" + alunos + "]";
	}

}
