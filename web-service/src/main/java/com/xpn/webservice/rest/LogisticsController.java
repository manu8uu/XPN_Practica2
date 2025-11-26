package com.xpn.webservice.rest;

import java.util.List;

import com.xpn.webservice.model.entitites.Pedidos;
import com.xpn.webservice.model.exceptions.InstanceNotFoundException;
import com.xpn.webservice.model.services.LogisticaService;
import static com.xpn.webservice.rest.ProductoConversor.toProductoDto;
import static com.xpn.webservice.rest.PedidoConversor.toPedidoDto;
import static com.xpn.webservice.rest.PedidoConversor.toPedidoDtos;
import static com.xpn.webservice.rest.PedidoConversor.toDetallePedidoDto;
import static com.xpn.webservice.rest.StockAlmacenConversor.toStockAlmacenDto;
import static com.xpn.webservice.rest.OrdenReabastecimientoConversor.toOrdenReabastecimientoDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;


@RestController
@RequestMapping("/api")
public class LogisticsController {

    @Autowired
    private LogisticaService logisticaService;

    @GetMapping("/productos/{productoId}")
    public ProductoDto consultarStockMinimo(@PathVariable Long productoId) {
        return toProductoDto(logisticaService.consultarStockMinimo(productoId));
    }
    
    @PostMapping("/pedidos")
    public PedidoDto registrarPedido(@Validated @RequestBody RegistrarPedidoParamsDto params) throws InstanceNotFoundException {
        return toPedidoDto(logisticaService.crearPedido(params.getClienteId(), params.getProductoId(),
                params.getCantidad(), params.getObservaciones()));
    }

    @GetMapping("/pedidos/{id}")
    public DetallePedidoDto detallesPedido(@PathVariable Long id) {
        return toDetallePedidoDto(logisticaService.verPedido(id));
    }

    @GetMapping("/pedidos")
    public List<PedidoDto> detallesPedidos() {
        return toPedidoDtos(logisticaService.verPedidos());
    }

    @PutMapping("/pedidos/{id}")
    public PedidoDto cambiarEstadoPedido(@PathVariable Long id, @RequestBody CambiarEstadoPedidoParamsDto params) {
        Pedidos.EstadoPedido estado = Pedidos.EstadoPedido.valueOf(params.getEstado().name());
        return toPedidoDto(logisticaService.cambiarEstadoPedido(id, estado));
    }

    @GetMapping("/stock/{productoId}/{almacenId}")
    public StockAlmacenDto consultarStock(@PathVariable Long productoId, @PathVariable Long almacenId) {
        return toStockAlmacenDto(logisticaService.consultarStock(productoId, almacenId));
    }
    
    @PostMapping("/stock/{productoId}/{almacenId}")
    public StockAlmacenDto actualizarStock(@PathVariable Long productoId, @PathVariable Long almacenId, @RequestBody ActualizarStockParamsDto params) {
        return toStockAlmacenDto(logisticaService.actualizarStock(productoId, almacenId, params.getCantidad()));
    }

    @PostMapping("/ordenesReabastecimiento")
    public OrdenReabastecimientoDto registrarOrdenAbastecimiento(@Validated @RequestBody RegistrarOrdenReabastecimientoParamsDto params) throws InstanceNotFoundException {
        return toOrdenReabastecimientoDto(logisticaService.crearOrdenReabastecimiento(
            params.getProductoId(), params.getCantidad(), params.getAlmacenDestinoId()));
    }
    @PostMapping("/recepcion/{ordenId}")
    public StockAlmacenDto recepcionProductos(@PathVariable Long ordenId) throws InstanceNotFoundException {
        return toStockAlmacenDto(logisticaService.recepcionProductos(ordenId));
    }

    @PostMapping("/pedidos/{pedidoId}/confirmar")
    public ResponseEntity<PedidoDto> confirmarPedido(@PathVariable Long pedidoId, @RequestParam Long almacenId) throws InstanceNotFoundException {
        
        Pedidos pedidoConfirmado = logisticaService.confirmarPedido(pedidoId, almacenId);
        return ResponseEntity.ok(toPedidoDto(pedidoConfirmado));
    }
}
