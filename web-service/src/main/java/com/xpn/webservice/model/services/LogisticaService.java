package com.xpn.webservice.model.services;

import java.util.List;

import com.xpn.webservice.model.entitites.*;
import com.xpn.webservice.model.exceptions.InstanceNotFoundException;

public interface LogisticaService {
    Pedidos crearPedido(Long clienteId, Long productoId, int cantidad, String observaciones) throws InstanceNotFoundException;
    Pedidos verPedido(Long pedidoId);
    List<Pedidos> verPedidos();
}