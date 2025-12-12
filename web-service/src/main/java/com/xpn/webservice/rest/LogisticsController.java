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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@Tag(name = "Logística", description = "API para gestión de Farmacéutica XPN")
public class LogisticsController {

    @Autowired
    private LogisticaService logisticaService;

    // PRODUCTOS
    @Operation(summary = "Consultar stock mínimo", description = "Devuelve la información del producto y su configuración de stock mínimo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Información recuperada con éxito", 
                     content = @Content(schema = @Schema(implementation = ProductoDto.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })

    @GetMapping("/productos/{productoId}")
    public ProductoDto consultarStockMinimo(@PathVariable Long productoId) {
        return toProductoDto(logisticaService.consultarStockMinimo(productoId));
    }
    
    // PEDIDOS
    @Operation(summary = "Registrar un nuevo pedido", description = "Crea un pedido para un cliente específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido registrado correctamente", 
                     content = @Content(schema = @Schema(implementation = PedidoDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Cliente o Producto no encontrado", content = @Content)
    })
    @PostMapping("/pedidos")
    public PedidoDto registrarPedido(@Validated @RequestBody RegistrarPedidoParamsDto params) throws InstanceNotFoundException {
        return toPedidoDto(logisticaService.crearPedido(params.getClienteId(), params.getProductoId(),
                params.getCantidad(), params.getObservaciones()));
    }

    @Operation(summary = "Obtener detalles de un pedido", description = "Recupera la información completa de un pedido por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles encontrados", 
                     content = @Content(schema = @Schema(implementation = DetallePedidoDto.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado", content = @Content)
    })
    @GetMapping("/pedidos/{id}")
    public DetallePedidoDto detallesPedido(@PathVariable Long id) {
        return toDetallePedidoDto(logisticaService.verPedido(id));
    }


    @Operation(summary = "Listar todos los pedidos", description = "Devuelve una lista con todos los pedidos registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista recuperada", 
                 content = @Content(array = @ArraySchema(schema = @Schema(implementation = PedidoDto.class))))
    @GetMapping("/pedidos")
    public List<PedidoDto> detallesPedidos() {
        return toPedidoDtos(logisticaService.verPedidos());
    }

    @Operation(summary = "Cambiar estado de un pedido", description = "Permite actualizar el estado (ej. CREADO, CONFIRMADO...) de un pedido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado actualizado", 
                     content = @Content(schema = @Schema(implementation = PedidoDto.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado", content = @Content)
    })
    @PutMapping("/pedidos/{id}")
    public PedidoDto cambiarEstadoPedido(@PathVariable Long id, @RequestBody CambiarEstadoPedidoParamsDto params) {
        Pedidos.EstadoPedido estado = Pedidos.EstadoPedido.valueOf(params.getEstado().name());
        return toPedidoDto(logisticaService.cambiarEstadoPedido(id, estado));
    }


    @Operation(summary = "Confirmar pedido", description = "Confirma un pedido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido confirmado exitosamente", 
                     content = @Content(schema = @Schema(implementation = PedidoDto.class))),
        @ApiResponse(responseCode = "404", description = "Pedido o Almacén no encontrado", content = @Content)
    })
    @PostMapping("/pedidos/{pedidoId}/confirmar")
    public ResponseEntity<PedidoDto> confirmarPedido(@PathVariable Long pedidoId, @RequestParam Long almacenId) throws InstanceNotFoundException {
        
        Pedidos pedidoConfirmado = logisticaService.confirmarPedido(pedidoId, almacenId);
        return ResponseEntity.ok(toPedidoDto(pedidoConfirmado));
    }
   
    //Stock ALMACÉN Y ORDENES REABASTECIMIENTO

    @Operation(summary = "Consultar stock en almacén", description = "Verifica la cantidad disponible de un producto en un almacén concreto.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock recuperado", 
                     content = @Content(schema = @Schema(implementation = StockAlmacenDto.class))),
        @ApiResponse(responseCode = "404", description = "Producto o Almacén no encontrado", content = @Content)
    })
    @GetMapping("/stock/{productoId}/{almacenId}")
    public StockAlmacenDto consultarStock(@PathVariable Long productoId, @PathVariable Long almacenId) {
        return toStockAlmacenDto(logisticaService.consultarStock(productoId, almacenId));
    }
    
    @Operation(summary = "Actualizar stock manualmente", description = "Modifica la cantidad de stock de un producto en un almacén.")
    @PostMapping("/stock/{productoId}/{almacenId}")
    public StockAlmacenDto actualizarStock(@PathVariable Long productoId, @PathVariable Long almacenId, @RequestBody ActualizarStockParamsDto params) {
        return toStockAlmacenDto(logisticaService.actualizarStock(productoId, almacenId, params.getCantidad()));
    }

    @Operation(summary = "Registrar orden de reabastecimiento", description = "Solicita nuevo stock a un proveedor para un almacén.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orden creada", 
                     content = @Content(schema = @Schema(implementation = OrdenReabastecimientoDto.class))),
        @ApiResponse(responseCode = "400", description = "Cantidad inválida", content = @Content)
    })
    @PostMapping("/ordenesReabastecimiento")
    public OrdenReabastecimientoDto registrarOrdenAbastecimiento(@Validated @RequestBody RegistrarOrdenReabastecimientoParamsDto params) throws InstanceNotFoundException {
        return toOrdenReabastecimientoDto(logisticaService.crearOrdenReabastecimiento(
            params.getProductoId(), params.getCantidad(), params.getAlmacenDestinoId()));
    }

    @Operation(summary = "Recepción de productos", description = "Procesa la llegada de una orden de reabastecimiento e incrementa el stock.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recepción procesada y stock actualizado", 
                     content = @Content(schema = @Schema(implementation = StockAlmacenDto.class))),
        @ApiResponse(responseCode = "404", description = "Orden no encontrada", content = @Content)
    })
    @PostMapping("/recepcion/{ordenId}")
    public StockAlmacenDto recepcionProductos(@PathVariable Long ordenId) throws InstanceNotFoundException {
        return toStockAlmacenDto(logisticaService.recepcionProductos(ordenId));
    }


}
