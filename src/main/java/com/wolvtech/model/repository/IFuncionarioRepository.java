package com.wolvtech.model.repository;

import com.wolvtech.model.entity.Funcionario;

public interface IFuncionarioRepository extends IRepository<Funcionario, Long> {
	
	Funcionario consultarUsuario(String usuario, String senha);
	
}
