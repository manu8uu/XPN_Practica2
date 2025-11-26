package com.xpn.webservice.rest;

public class RegistrarOrdenReabastecimientoParamsDto {

    private Long productoId;
    private Integer cantidad;
    private Long almacenDestinoId;


    public RegistrarOrdenReabastecimientoParamsDto() {}

    public RegistrarOrdenReabastecimientoParamsDto(Long productoId, Integer cantidad, Long almacenDestinoId) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.almacenDestinoId = almacenDestinoId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getAlmacenDestinoId() {
        return almacenDestinoId;
    }

    public void setAlmacenDestinoId(Long almacenDestinoId) {
        this.almacenDestinoId = almacenDestinoId;
    }
}
