package com.xpn.webservice.rest;

public class ActualizarStockParamsDto {

    private Integer cantidad;

    public ActualizarStockParamsDto() {}

    public ActualizarStockParamsDto(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
