package com.xpn.webservice.model.entitites;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PedidosDao extends CrudRepository<Pedidos, Long>, ListPagingAndSortingRepository<Pedidos, Long> {}
