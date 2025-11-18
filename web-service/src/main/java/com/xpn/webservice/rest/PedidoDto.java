package com.xpn.webservice.rest;

import java.time.LocalDateTime;

public class PedidoDto {

    public enum EstadoPedido {
        CREADO,
        PENDIENTE,
        CANCELADO,
        PROCESADO
    }

    private Long id;
    private Long clienteId;
    private LocalDateTime fecha;
    private Long productoId;
    private int cantidad;
    private String observaciones;
    private EstadoPedido estado;

    public PedidoDto() {}

    public PedidoDto(Long id, Long clienteId, LocalDateTime fecha, Long productoId, int cantidad, String observaciones, EstadoPedido estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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
    
    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
}
