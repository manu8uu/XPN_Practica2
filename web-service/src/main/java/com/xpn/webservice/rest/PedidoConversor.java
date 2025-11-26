package com.xpn.webservice.rest;

import com.xpn.webservice.model.entitites.Pedidos;

import java.util.List;

public class PedidoConversor {
    private PedidoConversor() {}

    public static PedidoDto toPedidoDto(Pedidos pedido) {
        return new PedidoDto(pedido.getId(), pedido.getCliente().getId(), pedido.getFecha(), pedido.getProducto().getId(), pedido.getCantidad(), pedido.getObservaciones(), PedidoDto.EstadoPedido.valueOf(pedido.getEstado().name()) );
    }

    public static List<PedidoDto> toPedidoDtos(List<Pedidos> pedidos) {
        return pedidos.stream().map(PedidoConversor::toPedidoDto).toList();
    }

    public static DetallePedidoDto toDetallePedidoDto(Pedidos pedido) {
        return new DetallePedidoDto(pedido.getId(), pedido.getCliente().getNombre(), pedido.getProducto().getNombre(), pedido.getCantidad(), pedido.getCantidad() * pedido.getProducto().getPrecio(), pedido.getObservaciones());
    }
}
