package com.xpn.webservice.rest;

import com.xpn.webservice.model.entitites.StocksAlmacen;

public class StockAlmacenConversor {

    private StockAlmacenConversor() {}

    public static StockAlmacenDto toStockAlmacenDto(StocksAlmacen stock) {
        return new StockAlmacenDto(stock.getId(), stock.getProducto().getId(), stock.getAlmacen().getId(), stock.getCantidad());
    }
}
