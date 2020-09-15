package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Coletivo extends Ambiente {
	
	public Coletivo() {}
	
	public Coletivo(Double tamanho, int distanciamentoMin, int lotacao) {
		this.setTamanho(tamanho);
		this.setDistanciamentoMin(distanciamentoMin);
		this.setLotacao(lotacao);
	}
	
}
