package br.gov.sp.fatec.projetomaven.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "pro_professor")
@PrimaryKeyJoinColumn(name = "pro_id")
public class Professor extends Usuario {
	
	@Column(name = "pro_titulo")
	private String titulo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "avaliador")
	private Set<Trabalho> trabalhosAvaliados;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizador")
	private Set<Evento> eventosOrganizados;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "responsavel")
	private Set<Ambiente> ambientesSobResponsabilidade;
	
	public Professor() {}
	
	public Professor(String nomeUsuario, String senha) {
		this.setNomeUsuario(nomeUsuario);
		this.setSenha(senha);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((ambientesSobResponsabilidade == null) ? 0 : ambientesSobResponsabilidade.hashCode());
		result = prime * result + ((eventosOrganizados == null) ? 0 : eventosOrganizados.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((trabalhosAvaliados == null) ? 0 : trabalhosAvaliados.hashCode());
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
		Professor other = (Professor) obj;
		if (ambientesSobResponsabilidade == null) {
			if (other.ambientesSobResponsabilidade != null)
				return false;
		} else if (!ambientesSobResponsabilidade.equals(other.ambientesSobResponsabilidade))
			return false;
		if (eventosOrganizados == null) {
			if (other.eventosOrganizados != null)
				return false;
		} else if (!eventosOrganizados.equals(other.eventosOrganizados))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (trabalhosAvaliados == null) {
			if (other.trabalhosAvaliados != null)
				return false;
		} else if (!trabalhosAvaliados.equals(other.trabalhosAvaliados))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Professor [titulo=" + titulo + ", trabalhosAvaliados=" + trabalhosAvaliados + ", eventosOrganizados="
				+ eventosOrganizados + ", ambientesSobResponsabilidade=" + ambientesSobResponsabilidade + "]";
	}

}
