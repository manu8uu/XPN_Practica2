package com.xpn.webservice.model.services;

import com.xpn.webservice.model.entitites.*;
import com.xpn.webservice.model.entitites.Pedidos.EstadoPedido;
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

    @Autowired
    private ProductosDao productosDao;

    @Autowired
    private PedidosDao pedidosDao;

    @Autowired
    private ClientesDao clientesDao;

    @Autowired
    private AlmacenesDao almacenesDao;

    @Autowired
    private StocksAlmacenDao stocksAlmacenDao;

    @Autowired
    private OrdenesReabastecimientoDao ordenesReabastecimientoDao;

    @Override
    public Productos consultarStockMinimo(Long productoId) {
        Optional<Productos> producto = productosDao.findById(productoId);
        if (!producto.isPresent()) {
            throw new RuntimeException("Producto no encontrado: " + productoId);
        }

        return producto.get();
    }

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

    @Override
    public Pedidos cambiarEstadoPedido(Long pedidoId, Pedidos.EstadoPedido estado) {
        Optional<Pedidos> pedido = pedidosDao.findById(pedidoId);
        if (pedido.isEmpty()) {
            throw new RuntimeException("Pedido no encontrado: " + pedidoId);
        }

        pedido.get().setEstado(estado);
        pedidosDao.save(pedido.get());

        return pedido.get();
    }

    @Override
    public StocksAlmacen consultarStock(Long productoId, Long almacenId) {
        Optional<Productos> producto = productosDao.findById(productoId);
        if (!producto.isPresent()) {
            throw new RuntimeException("Producto no encontrado: " + productoId);
        }

        Optional<Almacenes> almacen = almacenesDao.findById(almacenId);
        if (!almacen.isPresent()) {
            throw new RuntimeException("Almacén no encontrado: " + almacenId);
        }

        StocksAlmacen stock = stocksAlmacenDao.findByProducto_IdAndAlmacen_Id(producto.get().getId(), almacen.get().getId());

        return stock;
    }
    
    @Override
    public StocksAlmacen actualizarStock(Long productoId, Long almacenId, int cantidad) {
        Optional<Productos> producto = productosDao.findById(productoId);
        if (!producto.isPresent()) {
            throw new RuntimeException("Producto no encontrado: " + productoId);
        }

        Optional<Almacenes> almacen = almacenesDao.findById(almacenId);
        if (!almacen.isPresent()) {
            throw new RuntimeException("Almacén no encontrado: " + almacenId);
        }

        StocksAlmacen stock = stocksAlmacenDao.findByProducto_IdAndAlmacen_Id(producto.get().getId(), almacen.get().getId());
        
        stock.setCantidad(cantidad);
        stocksAlmacenDao.save(stock);

        return stock;

    }

    @Override //Podría ser interesante meterle directamente el id de pedido. Asi, cuando haga la recepción de productos, se puede confirmar directamente el pedido
    public OrdenesReabastecimiento crearOrdenReabastecimiento(Long productoId, int cantidad, Long almacenDestinoId) throws InstanceNotFoundException {
        Optional<Productos> producto = productosDao.findById(productoId);
        if (!producto.isPresent()) {
            throw new InstanceNotFoundException("project.entities.productos", productoId);
        }

        Optional<Almacenes> almacenDestino = almacenesDao.findById(almacenDestinoId);
        if (!almacenDestino.isPresent()) {
            throw new InstanceNotFoundException("project.entities.almacenes", almacenDestinoId);
        }

        OrdenesReabastecimiento orden = new OrdenesReabastecimiento(producto.get(), cantidad, LocalDateTime.now(), almacenDestino.get());
        ordenesReabastecimientoDao.save(orden);

        return orden;
    }

        // Recepción de productos en almacén
    @Override
    public StocksAlmacen recepcionProductos(Long ordenId) throws InstanceNotFoundException {
        OrdenesReabastecimiento orden = ordenesReabastecimientoDao.findById(ordenId)
            .orElseThrow(() -> new InstanceNotFoundException("Orden no encontrado", ordenId.toString()));

        int cantidad = consultarStock(orden.getProducto().getId(), orden.getAlmacenDestino().getId()).getCantidad() + orden.getCantidad();
        StocksAlmacen stock = actualizarStock(orden.getProducto().getId(), orden.getAlmacenDestino().getId(), cantidad);
        ordenesReabastecimientoDao.delete(orden);
        return stock;
        
    }

        // Confirmar/cancelar pedido
    @Override
    public Pedidos confirmarPedido(Long pedidoId, Long almacenId) throws InstanceNotFoundException {
        Pedidos pedido = pedidosDao.findById(pedidoId)
            .orElseThrow(() -> new InstanceNotFoundException("Pedido no encontrado", pedidoId.toString()));

        if (!almacenesDao.existsById(almacenId)) {
        throw new InstanceNotFoundException("Almacen no encontrado", almacenId.toString());
    }

        if(pedido.getEstado().equals(EstadoPedido.PROCESADO) || pedido.getEstado().equals(EstadoPedido.CANCELADO)){
            return pedido;
        }
        int diferencia = pedido.getCantidad() - consultarStock(pedidoId, almacenId).getCantidad();

        if (diferencia > 0) {
            pedido.setEstado(EstadoPedido.PENDIENTE);
            pedido.setCantidad(diferencia);
            pedidosDao.save(pedido);
            actualizarStock(pedidoId, almacenId,0);
            crearOrdenReabastecimiento(pedidoId, diferencia, almacenId);
        }else{
            pedido.setEstado(EstadoPedido.PROCESADO);
            pedidosDao.save(pedido);
            actualizarStock(pedidoId, almacenId, consultarStock(pedidoId, almacenId).getCantidad()- pedido.getCantidad());
        }

        return pedidosDao.save(pedido);

    }

        //Consultar límite de stock de cada producto
   
}
