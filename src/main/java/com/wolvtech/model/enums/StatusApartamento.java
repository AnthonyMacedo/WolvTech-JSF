package com.wolvtech.model.enums;

public enum StatusApartamento {

		DISPONIVEL("Disponível"),
		OCUPADO("Ocupado"),
		RESERVADO("Reservado");
		
		private String descricao;
		
		StatusApartamento(String descricao) {
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return this.descricao;
		}
	}

