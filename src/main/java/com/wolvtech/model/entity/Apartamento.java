package com.wolvtech.model.entity;

import java.io.Serializable;

import com.wolvtech.model.enums.StatusApartamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "apartamento")
public class Apartamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "num_quarto", length = 3, unique = true, nullable = false)
	private String numQuarto;

	@NotNull
	@Column(name = "qtd_ocupantes")
	private Integer qtdOcupantes;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status_apartamento", length = 11)
	private StatusApartamento statusApartamento;

	public Apartamento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumQuarto() {
		return numQuarto;
	}

	public void setNumQuarto(String numQuarto) {
		this.numQuarto = numQuarto;
	}

	public Integer getQtdOcupantes() {
		return qtdOcupantes;
	}

	public void setQtdOcupantes(Integer qtdOcupantes) {
		this.qtdOcupantes = qtdOcupantes;
	}

	public StatusApartamento getStatusApartamento() {
		return statusApartamento;
	}

	public void setStatusApartamento(StatusApartamento statusApartamento) {
		this.statusApartamento = statusApartamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Apartamento other = (Apartamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
