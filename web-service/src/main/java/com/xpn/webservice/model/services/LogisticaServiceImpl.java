package com.xpn.webservice.model.services;

import com.xpn.webservice.model.entitites.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogisticaServiceImpl implements LogisticaService {
    // Mostrar detalles del pedido
    // Confirmar/cancelar pedido
    // Consultar stock de un producto en un almacén determinado
    // Registrar nuevos pedidos
    //registrar orden de reabastecimiento
    // Recepción de productos en almacén
    //Consultar límite de stock de cada producto

    @Autowired
    private ProductosDao productosDao;

    @Autowired
    private PedidosDao pedidosDao;

    public Pedidos verPedido(Long pedidoId) {
        Optional<Pedidos> pedido = pedidosDao.findById(pedidoId);
        if (pedido.isEmpty()) {
            throw new RuntimeException("Pedido no encontrado: " + pedidoId);
        }
        return pedido.get();
    }
}
