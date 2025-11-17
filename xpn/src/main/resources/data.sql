
INSERT INTO Productos (nombre, precio) VALUES
('Ibuprofeno 600mg', 3.50),
('Paracetamol 500mg', 1.80),
('Amoxicilina 500mg', 8.95),
('Omeprazol 20mg', 5.20),
('Jarabe para la Tos (Dextrometorfano)', 7.45);


INSERT INTO Almacenes (nombre, ubicacion) VALUES
('Almacén Central', 'Avenida Principal 123, Madrid'),
('Almacén Norte', 'Calle del Sol 45, Barcelona'),
('Almacén Sur', 'Polígono Industrial, Sevilla');

INSERT INTO StockAlmacen (producto_id, almacen_id, cantidad) VALUES
(1, 1, 5000),  -- 5000 en Almacén Central
(1, 2, 2000),  -- 2000 en Almacén Norte
(1, 3, 1000);  -- 1000 en Almacén Sur

INSERT INTO StockAlmacen (producto_id, almacen_id, cantidad) VALUES
(2, 1, 8000),
(2, 2, 4500);

INSERT INTO StockAlmacen (producto_id, almacen_id, cantidad) VALUES
(3, 1, 1200);

INSERT INTO StockAlmacen (producto_id, almacen_id, cantidad) VALUES
(4, 3, 3000);

INSERT INTO StockAlmacen (producto_id, almacen_id, cantidad) VALUES
(5, 1, 2500),
(5, 2, 1500);


INSERT INTO Pedidos (codCliente, nombreCliente, fecha) VALUES
('C1001', 'Farmacia Sanitas', '2025-11-15'),
('C1002', 'Centro de Salud Alameda', '2025-11-16');

INSERT INTO OrdenesReabastecimiento (productoId, cantidad, fecha, almacenDestinoId) VALUES
(2, 6000, '2025-11-17', 3);

INSERT INTO OrdenesReabastecimiento (productoId, cantidad, fecha, almacenDestinoId) VALUES
(1, 10000, '2025-11-17', 1);