package com.wolvtech.model.dao;

import javax.inject.Named;

import com.wolvtech.model.entity.Endereco;
import com.wolvtech.model.repository.IEnderecoRepository;

@Named
public class EnderecoDaoImpl extends DaoGeneric<Endereco, Long> implements IEnderecoRepository {

}
