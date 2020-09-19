package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.sp.fatec.projetomaven.entity.common.AutoId;

@Entity
@Table(name = "amb_ambiente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="amb_tipo_ambiente", 
	discriminatorType = DiscriminatorType.INTEGER)
@AttributeOverride(name = "id", column = @Column(name = "amb_id"))
public class Ambiente extends AutoId {
	
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
		int result = super.hashCode();
		result = prime * result + distanciamentoMin;
		result = prime * result + lotacao;
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
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
		Ambiente other = (Ambiente) obj;
		if (distanciamentoMin != other.distanciamentoMin)
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
		return "Ambiente [tamanho=" + tamanho + ", distanciamentoMin=" + distanciamentoMin + ", lotacao=" + lotacao
				+ ", responsavel=" + responsavel + "]";
	}

}

