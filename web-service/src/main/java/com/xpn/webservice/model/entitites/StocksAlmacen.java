package com.xpn.webservice.model.entitites;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_almacen")
public class StocksAlmacen {

    private Long id;
    private Productos producto;
    private Almacenes almacen;
    private Integer cantidad;

    public StocksAlmacen() {}

    public StocksAlmacen(Long id, Productos producto, Almacenes almacen, Integer cantidad) {
        this.id = id;
        this.producto = producto;
        this.almacen = almacen;
        this.cantidad = cantidad;
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

    @ManyToOne(optional=false, fetch= FetchType.LAZY)
    @JoinColumn(name = "almacen_id")
    public Almacenes getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacenes almacen) {
        this.almacen = almacen;
    }

    @Column(name = "cantidad")
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
