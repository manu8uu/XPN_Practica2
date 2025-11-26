-- Datos iniciales: clientes
INSERT INTO clientes (nombre, tipo) VALUES
('Farmacia Sanitas', 'FARMACIA'),
('Centro de Salud Alameda', 'HOSPITAL');

-- Datos iniciales: productos
INSERT INTO productos (nombre, precio, stock_minimo) VALUES
('Ibuprofeno 600mg', 3.50, 50),
('Paracetamol 500mg', 1.80, 50),
('Amoxicilina 500mg', 8.95, 50),
('Omeprazol 20mg', 5.20, 50),
('Jarabe para la Tos (Dextrometorfano)', 7.45, 50);

-- Datos iniciales: almacenes
INSERT INTO almacenes (nombre, ubicacion) VALUES
('Almacén Central', 'Avenida Principal 123, Madrid'),
('Almacén Norte', 'Calle del Sol 45, Barcelona'),
('Almacén Sur', 'Polígono Industrial, Sevilla');

-- Datos iniciales: stock_almacen
INSERT INTO stock_almacen (producto_id, almacen_id, cantidad) VALUES
(1, 1, 5000),
(1, 2, 2000),
(1, 3, 1000),
(2, 1, 8000),
(2, 2, 4500),
(3, 1, 1200),
(4, 3, 3000),
(5, 1, 2500),
(5, 2, 1500);

-- Datos iniciales: pedidos
INSERT INTO pedidos (cliente_id, fecha, producto_id, cantidad, observaciones, estado) VALUES
(1, '2025-11-15', 1, 50, 'Urgente', 'CREADO'),
(2, '2025-11-16', 2, 30, 'Entrega estándar', 'CREADO');

-- Datos iniciales: ordenes_reabastecimiento
INSERT INTO ordenes_reabastecimiento (producto_id, cantidad, fecha, almacen_destino_id) VALUES
(2, 6000, '2025-11-17', 3),
(1, 10000, '2025-11-17', 1);