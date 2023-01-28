package com.wolvtech.model.repository;

import com.wolvtech.model.entity.Apartamento;

public interface IApartamentoRepository extends IRepository<Apartamento, Long> {
	
	public boolean validarQuartoCadastrado(String numQuarto);
	
	public Apartamento verificaSeTemId(String numQuarto);
	
}
