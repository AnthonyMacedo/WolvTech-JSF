package com.wolvtech.model.dao;

import javax.inject.Named;

import com.wolvtech.model.entity.Reservas;
import com.wolvtech.model.repository.IReservasRepository;

@Named
public class ReservasDao extends DaoGeneric<Reservas, Long> implements IReservasRepository {

}
