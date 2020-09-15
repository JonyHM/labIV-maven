package br.gov.sp.fatec.projetomaven.helpers;

public class Calc {

	private Float first;
	private Float second;
	
	public Calc() {
		this.first = new Float(1);
		this.second = new Float(1);
	}
	
	public Calc(Float first, Float second) {
		this.first = first;
		this.second = second;
	}
	
	public Float sum() {
		return first + second;
	}
	
	public Float subtraction() {
		return first - second;
	}
	
	public Float division() {
		return first > second 
				? first / second
				: second / first;
	}
	
	public Float multiplication() {
		return first * second;
	}
}
