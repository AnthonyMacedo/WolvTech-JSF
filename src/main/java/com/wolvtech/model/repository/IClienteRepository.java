package com.wolvtech.model.repository;

import com.wolvtech.model.entity.Cliente;

public interface IClienteRepository extends IRepository<Cliente, Long> {

	Cliente verificaCadastroCliente(Long id);

}
