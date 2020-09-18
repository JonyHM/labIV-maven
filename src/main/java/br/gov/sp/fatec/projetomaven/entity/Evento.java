package br.gov.sp.fatec.projetomaven.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.gov.sp.fatec.projetomaven.entity.common.AutoId;

@MappedSuperclass
public class Evento extends AutoId {
	
	@Column(name = "eve_titulo")
	private String titulo;
	
	@Column(name = "eve_local")
	private String local;
	
	@Column(name = "eve_data_hora_agendamento")
	private Date dataHoraAgendamento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pro_organizador_id")
	private Professor organizador;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Date getDataHoraAgendamento() {
		return dataHoraAgendamento;
	}

	public void setDataHoraAgendamento(Date dataHoraAgendamento) {
		this.dataHoraAgendamento = dataHoraAgendamento;
	}

	public Professor getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Professor organizador) {
		this.organizador = organizador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataHoraAgendamento == null) ? 0 : dataHoraAgendamento.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((organizador == null) ? 0 : organizador.hashCode());
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
		Evento other = (Evento) obj;
		if (dataHoraAgendamento == null) {
			if (other.dataHoraAgendamento != null)
				return false;
		} else if (!dataHoraAgendamento.equals(other.dataHoraAgendamento))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (organizador == null) {
			if (other.organizador != null)
				return false;
		} else if (!organizador.equals(other.organizador))
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
		return "Evento [titulo=" + titulo + ", local=" + local + ", dataHoraAgendamento=" + dataHoraAgendamento
				+ ", organizador=" + organizador + "]";
	}

}
