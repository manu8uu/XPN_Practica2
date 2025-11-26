package com.xpn.webservice.rest;

public class CambiarEstadoPedidoParamsDto {
    private PedidoDto.EstadoPedido estado;

    public CambiarEstadoPedidoParamsDto() {}

    public CambiarEstadoPedidoParamsDto(PedidoDto.EstadoPedido estado) {
        this.estado = estado;
    }

    public PedidoDto.EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(PedidoDto.EstadoPedido estado) {
        this.estado = estado;
    }
}
