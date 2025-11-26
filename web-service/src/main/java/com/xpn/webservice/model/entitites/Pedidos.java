package com.xpn.webservice.model.entitites;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedidos {

    public enum EstadoPedido {
        CREADO,
        PENDIENTE,
        CANCELADO,
        PROCESADO
    }

    private Long id;
    private Clientes cliente;
    private LocalDateTime fecha;
    private Productos producto;
    private int cantidad;
    private String observaciones;
    private EstadoPedido estado;

    public Pedidos() {}


    public Pedidos(Clientes cliente, LocalDateTime fecha, Productos producto, int cantidad, String observaciones, EstadoPedido estado) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.producto = producto;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional=false, fetch= FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    @Column(name = "fecha", nullable = false)
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @ManyToOne(optional=false, fetch= FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
}
