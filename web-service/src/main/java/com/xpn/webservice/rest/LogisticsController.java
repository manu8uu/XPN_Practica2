package com.xpn.webservice.rest;

import java.util.List;

import com.xpn.webservice.model.exceptions.InstanceNotFoundException;
import com.xpn.webservice.model.services.LogisticaService;
import static com.xpn.webservice.rest.PedidoConversor.toPedidoDto;
import static com.xpn.webservice.rest.PedidoConversor.toPedidoDtos;
import static com.xpn.webservice.rest.PedidoConversor.toDetallePedidoDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api")
public class LogisticsController {

    @Autowired
    private LogisticaService logisticaService;

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
}
