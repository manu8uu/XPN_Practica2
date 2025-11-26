package com.xpn.webservice.rest;

public class StockAlmacenDto {

    private Long id;
    private Long productoId;
    private Long almacenId;
    private Integer cantidad;

    public StockAlmacenDto() {}

    public StockAlmacenDto(Long id, Long productoId, Long almacenId, Integer cantidad) {
        this.id = id;
        this.productoId = productoId;
        this.almacenId = almacenId;
        this.cantidad = cantidad;
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

    public Long getAlmacenId() {
        return almacenId;
    }

    public void setAlmacenId(Long almacenId) {
        this.almacenId = almacenId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
