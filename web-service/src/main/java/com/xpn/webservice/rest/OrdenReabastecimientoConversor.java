package com.xpn.webservice.rest;

import com.xpn.webservice.model.entitites.OrdenesReabastecimiento;

public class OrdenReabastecimientoConversor {

    private OrdenReabastecimientoConversor() {}

    public static OrdenReabastecimientoDto toOrdenReabastecimientoDto(OrdenesReabastecimiento ordenesReabastecimiento) {
        return new OrdenReabastecimientoDto(ordenesReabastecimiento.getId(), ordenesReabastecimiento.getProducto().getId(),
         ordenesReabastecimiento.getCantidad(), ordenesReabastecimiento.getAlmacenDestino().getId(), ordenesReabastecimiento.getFecha().toString());
    }
    
}
