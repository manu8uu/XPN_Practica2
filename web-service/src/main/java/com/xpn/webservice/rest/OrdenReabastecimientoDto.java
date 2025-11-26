package com.xpn.webservice.rest;

public class OrdenReabastecimientoDto {

    private Long id;
    private Long productoId;
    private Integer cantidad;
    private Long almacenDestinoId;
    private String fecha;

    public OrdenReabastecimientoDto() {}

    public OrdenReabastecimientoDto(Long id, Long productoId, Integer cantidad, Long almacenDestinoId, String fecha) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.almacenDestinoId = almacenDestinoId;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
