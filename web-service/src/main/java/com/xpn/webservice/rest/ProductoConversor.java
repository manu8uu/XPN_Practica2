package com.xpn.webservice.rest;

import com.xpn.webservice.model.entitites.Productos;

public class ProductoConversor {

    private ProductoConversor() {}

    public static ProductoDto toProductoDto(Productos producto) {
        return new ProductoDto(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStockMinimo());
    }
    
}
