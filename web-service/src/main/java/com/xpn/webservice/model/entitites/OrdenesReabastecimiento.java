package com.xpn.webservice.model.entitites;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "ordenes_reabastecimiento")
public class OrdenesReabastecimiento {

    private Long id;
    private Productos producto;
    private Integer cantidad;
    private LocalDateTime fecha;
    private Almacenes almacenDestino;

    public OrdenesReabastecimiento() {}

    public OrdenesReabastecimiento(Productos producto, Integer cantidad, LocalDateTime fecha, Almacenes almacenDestino) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.almacenDestino = almacenDestino;
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
    @JoinColumn(name = "producto_id")
    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }                        

    @Column(name = "cantidad")
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "fecha")
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @ManyToOne(optional=false, fetch= FetchType.LAZY)
    @JoinColumn(name = "almacen_destino_id")
    public Almacenes getAlmacenDestino() {
        return almacenDestino;
    }

    public void setAlmacenDestino(Almacenes almacenDestino) {
        this.almacenDestino = almacenDestino;
    }
    
}
