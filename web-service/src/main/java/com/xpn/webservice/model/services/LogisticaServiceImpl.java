package com.xpn.webservice.model.services;

import com.xpn.webservice.model.entitites.*;
import com.xpn.webservice.model.exceptions.InstanceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Sort;
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

    @Autowired
    private ClientesDao clientesDao;

    @Override
    public Pedidos crearPedido(Long clienteId, Long productoId, int cantidad, String observaciones) throws InstanceNotFoundException {
        Optional<Clientes> cliente = clientesDao.findById(clienteId);

        if (!cliente.isPresent()) {
            throw new InstanceNotFoundException("project.entities.clientes", clienteId);
        }

        Optional<Productos> producto = productosDao.findById(productoId);
        if (!producto.isPresent()) {
            throw new InstanceNotFoundException("project.entities.productos", productoId);
        }

        Pedidos pedido = new Pedidos(cliente.get(), LocalDateTime.now(), producto.get(), cantidad, observaciones, Pedidos.EstadoPedido.CREADO);
        pedidosDao.save(pedido);
        return pedido;
    }

    @Override
    public Pedidos verPedido(Long pedidoId) {
        Optional<Pedidos> pedido = pedidosDao.findById(pedidoId);
        if (pedido.isEmpty()) {
            throw new RuntimeException("Pedido no encontrado: " + pedidoId);
        }
        return pedido.get();
    }

    @Override
    public List<Pedidos> verPedidos() {
        return pedidosDao.findAll(Sort.by(Sort.Direction.ASC, "fecha"));
    }

}
