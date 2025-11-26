-- Elimina tablas si existen
DROP TABLE IF EXISTS ordenes_reabastecimiento;
DROP TABLE IF EXISTS stock_almacen;
DROP TABLE IF EXISTS pedidos;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS almacenes;
DROP TABLE IF EXISTS clientes;

-- Tabla clientes
CREATE TABLE clientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    CONSTRAINT clientes_pk PRIMARY KEY (id)
);

-- Tabla productos
CREATE TABLE productos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock_minimo INT NOT NULL,
    CONSTRAINT productos_pk PRIMARY KEY (id)
);

-- Tabla pedidos
CREATE TABLE pedidos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cliente_id BIGINT NOT NULL,
    fecha DATETIME NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    observaciones VARCHAR(255),
    estado VARCHAR(20) NOT NULL,
    CONSTRAINT pedidos_pk PRIMARY KEY (id),
    CONSTRAINT pedidos_cliente_fk FOREIGN KEY (cliente_id) REFERENCES clientes (id),
    CONSTRAINT pedidos_producto_fk FOREIGN KEY (producto_id) REFERENCES productos (id)
);

-- Tabla almacenes
CREATE TABLE almacenes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL,
    CONSTRAINT almacenes_pk PRIMARY KEY (id)
);

-- Tabla stock_almacen
CREATE TABLE stock_almacen (
    id BIGINT NOT NULL AUTO_INCREMENT,
    producto_id BIGINT NOT NULL,
    almacen_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    CONSTRAINT stock_almacen_pk PRIMARY KEY (id),
    UNIQUE (producto_id, almacen_id),
    CONSTRAINT stock_almacen_producto_fk FOREIGN KEY (producto_id) REFERENCES productos (id),
    CONSTRAINT stock_almacen_almacen_fk FOREIGN KEY (almacen_id) REFERENCES almacenes (id)
);

-- Tabla ordenes_reabastecimiento
CREATE TABLE ordenes_reabastecimiento (
    id BIGINT NOT NULL AUTO_INCREMENT,
    producto_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    fecha DATETIME NOT NULL,
    almacen_destino_id BIGINT NOT NULL,
    CONSTRAINT ordenes_reabastecimiento_pk PRIMARY KEY (id),
    CONSTRAINT ordenes_reabastecimiento_producto_fk FOREIGN KEY (producto_id) REFERENCES productos (id),
    CONSTRAINT ordenes_reabastecimiento_almacen_fk FOREIGN KEY (almacen_destino_id) REFERENCES almacenes (id)
);