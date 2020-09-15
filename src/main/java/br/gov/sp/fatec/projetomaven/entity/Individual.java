package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Individual extends Ambiente {

	public Individual() {}
	
	public Individual(Double tamanho, int distanciamentoMin, int lotacao) {
		this.setTamanho(tamanho);
		this.setDistanciamentoMin(distanciamentoMin);
		this.setLotacao(lotacao);
	}
}
