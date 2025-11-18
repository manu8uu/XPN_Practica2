package com.xpn.webservice.model.entitites;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedidos {

    public enum TipoProducto {
		MEDICAMENTO,
        INSUMO;
	}

    private Long id;
    private Clientes cliente;
    private Date fecha;
    private Productos producto;
    private int cantidad;
    private String observaciones;

    public Pedidos() {}


    public Pedidos(Long id, Clientes cliente, Date fecha, Productos producto, int cantidad, String observaciones) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.producto = producto;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
}
