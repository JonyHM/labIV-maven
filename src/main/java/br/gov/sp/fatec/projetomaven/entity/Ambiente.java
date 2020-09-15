package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "amb_ambiente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="amb_tipo_ambiente", 
	discriminatorType = DiscriminatorType.INTEGER)
public class Ambiente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "amb_id")
	private Long id;
	
	@Column(name = "amb_tamanho")
	private Double tamanho;
	
	@Column(name = "amb_distanciamento_min")
	private int distanciamentoMin;
	
	@Column(name = "amb_lotacao")
	private int lotacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pro_responsavel_id")
	private Professor responsavel;

	public Ambiente() {}
	
	public Ambiente(Double tamanho, int distanciamentoMin, int lotacao) {
		this.setTamanho(tamanho);
		this.setDistanciamentoMin(distanciamentoMin);
		this.setLotacao(lotacao);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTamanho() {
		return tamanho;
	}

	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}

	public int getDistanciamentoMin() {
		return distanciamentoMin;
	}

	public void setDistanciamentoMin(int distanciamentoMin) {
		this.distanciamentoMin = distanciamentoMin;
	}

	public int getLotacao() {
		return lotacao;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}

	public Professor getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Professor responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + distanciamentoMin;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + lotacao;
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ambiente other = (Ambiente) obj;
		if (distanciamentoMin != other.distanciamentoMin)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lotacao != other.lotacao)
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (tamanho == null) {
			if (other.tamanho != null)
				return false;
		} else if (!tamanho.equals(other.tamanho))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ambiente [id=" + id + ", tamanho=" + tamanho + ", distanciamentoMin=" + distanciamentoMin + ", lotacao="
				+ lotacao + ", responsavel=" + responsavel + "]";
	}
}

