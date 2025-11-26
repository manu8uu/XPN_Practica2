package com.xpn.webservice.model.entitites;

import jakarta.persistence.*;

@Entity
public class Productos {

    private Long id;
    private String nombre;
    private Double precio;
    private Integer stockMinimo;

    public Productos() {}
    
    public Productos(Long id, String nombre, Double precio, Integer stockMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    @Column(name = "stock_minimo")
    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }
}
