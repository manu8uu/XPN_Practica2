package com.xpn.webservice.rest;

import com.xpn.webservice.model.entitites.Pedidos;

public class PedidoConversor {
    private PedidoConversor() {}

    public static DetallePedidoDto toDetallePedidoDto(Pedidos pedido) {
        return new DetallePedidoDto(pedido.getId(), pedido.getCliente().getNombre(), pedido.getProducto().getNombre(), pedido.getCantidad(), pedido.getCantidad() * pedido.getProducto().getPrecio(), pedido.getObservaciones());
    }
}
