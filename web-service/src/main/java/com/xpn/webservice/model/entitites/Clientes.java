package com.xpn.webservice.model.entitites;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Clientes {

    public enum TipoCliente {
		HOSPITAL,
        FARMACIA;
	}

    private Long id;
    private String nombre;
    private TipoCliente tipo;

    public Clientes() {}

    public Clientes(Long id, String nombre, TipoCliente tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }
    
}
