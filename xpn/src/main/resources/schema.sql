DROP TABLE IF EXISTS OrdenesReabastecimiento;
DROP TABLE IF EXISTS StockAlmacen;
DROP TABLE IF EXISTS Pedidos;
DROP TABLE IF EXISTS Productos;
DROP TABLE IF EXISTS Almacenes;


CREATE TABLE Productos (
    producto_id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (producto_id)
);

CREATE TABLE Pedidos (
    pedidoId BIGINT NOT NULL AUTO_INCREMENT,
    codCliente VARCHAR(10) NOT NULL,
    nombreCliente VARCHAR(50) NOT NULL,
    fecha DATE NOT NULL,
    PRIMARY KEY (pedidoId)
);

CREATE TABLE Almacenes (
    almacen_id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL,
    PRIMARY KEY (almacen_id)
);

CREATE TABLE StockAlmacen (
    stock_almacen_id BIGINT NOT NULL AUTO_INCREMENT,
    producto_id BIGINT NOT NULL,
    almacen_id BIGINT NOT NULL,
    cantidad INTEGER NOT NULL,
    PRIMARY KEY (stock_almacen_id),
    UNIQUE (producto_id, almacen_id),
    FOREIGN KEY (producto_id) REFERENCES Productos (producto_id),
    FOREIGN KEY (almacen_id) REFERENCES Almacenes (almacen_id)
);

CREATE TABLE OrdenesReabastecimiento (
    ordenId BIGINT NOT NULL AUTO_INCREMENT,
    productoId BIGINT NOT NULL,
    cantidad INT NOT NULL,
    fecha DATE NOT NULL,
    almacenDestinoId BIGINT NOT NULL,
    PRIMARY KEY (ordenId),
    FOREIGN KEY (productoId) REFERENCES Productos (producto_id),
    FOREIGN KEY (almacenDestinoId) REFERENCES Almacenes (almacen_id)
);