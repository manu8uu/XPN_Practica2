package com.xpn.webservice.model.services;

import java.util.List;

import com.xpn.webservice.model.entitites.*;
import com.xpn.webservice.model.exceptions.InstanceNotFoundException;

public interface LogisticaService {
    Productos consultarStockMinimo(Long productoId);
    Pedidos crearPedido(Long clienteId, Long productoId, int cantidad, String observaciones) throws InstanceNotFoundException;
    Pedidos verPedido(Long pedidoId);
    List<Pedidos> verPedidos();
    Pedidos cambiarEstadoPedido(Long pedidoId, Pedidos.EstadoPedido estado);
    StocksAlmacen consultarStock(Long productoId, Long almacenId);
    StocksAlmacen actualizarStock(Long productoId, Long almacenId, int cantidad);
    OrdenesReabastecimiento crearOrdenReabastecimiento(Long productoId, int cantidad, Long almacenDestinoId) throws InstanceNotFoundException;
    StocksAlmacen recepcionProductos (Long ordenId)throws InstanceNotFoundException;
    Pedidos confirmarPedido(Long pedidoId,Long almacenId) throws InstanceNotFoundException;

}