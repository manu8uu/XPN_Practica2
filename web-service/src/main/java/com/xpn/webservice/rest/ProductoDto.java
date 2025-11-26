package com.xpn.webservice.rest;

public class ProductoDto {
    
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stockMinimo;

    public ProductoDto() {}

    public ProductoDto(Long id, String nombre, Double precio, Integer stockMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}
