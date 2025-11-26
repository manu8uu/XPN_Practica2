package com.xpn.webservice.model.entitites;

import org.springframework.data.repository.CrudRepository;

public interface StocksAlmacenDao extends CrudRepository<StocksAlmacen, Long> {

    StocksAlmacen findByProducto_IdAndAlmacen_Id(Long productoId, Long almacenId);
}

