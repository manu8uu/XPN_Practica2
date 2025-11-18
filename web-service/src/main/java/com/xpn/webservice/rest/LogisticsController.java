package com.xpn.webservice.rest;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpn.webservice.model.services.LogisticaService;
import static com.xpn.webservice.rest.PedidoConversor.toDetallePedidoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class LogisticsController {

    @Autowired
    private LogisticaService logisticaService;

    @GetMapping("/pedidos/{id}")
    public DetallePedidoDto detallesPedido(@PathVariable Long id) {
        return toDetallePedidoDto(logisticaService.verPedido(id));
    }
}
