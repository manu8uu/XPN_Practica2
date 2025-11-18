package com.xpn.webservice.rest;


public class RegistrarPedidoParamsDto {
    private Long clienteId;
    private Long productoId;
    private int cantidad;
    private String observaciones;

    public RegistrarPedidoParamsDto(Long clienteId, Long productoId, int cantidad, String observaciones) {
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
