-- 1. MARCAS
INSERT INTO marca (nombre) VALUES ('Adidas');
INSERT INTO marca (nombre) VALUES ('Nike');
INSERT INTO marca (nombre) VALUES ('Joma');
INSERT INTO marca (nombre) VALUES ('Puma');   -- Marca sin productos (situación 2)

-- 2. CATEGORÍAS
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Balones', 'Balones de fútbol de todas las categorías', '/imagenCat.jpg');
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Botas', 'Calzado técnico para fútbol', '/imagenCat.jpg');
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Equipaciones', 'Camisetas y conjuntos oficiales', '/imagenCat.jpg');
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Protecciones', 'Espinilleras y protecciones', '/imagenCat.jpg');
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Porterías', 'Porterías y accesorios de portero', '/imagenCat.jpg');
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Accesorios', 'Accesorios varios de fútbol', null); -- Categoría sin productos (situación 6)

-- 3. PRODUCTOS (marca_id en vez de marca)
-- Adidas = 1, Nike = 2, Joma = 3
INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8410013010513', 'Balón Champions League', 'Balón oficial de la UEFA Champions League, fabricado con paneles termosoldados para una superficie perfectamente lisa. Su núcleo de butilo garantiza una retención del aire excepcional. Diseñado para partidos de alta competición en césped natural y artificial. El preferido por los mejores jugadores del mundo.', '/imagenes/productos/productosImg.jpg', 34.99, 10, 1);

INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8480017358073', 'Botas Predator Elite', 'Botas de fútbol con tecnología Predator para un control y potencia de disparo superiores. Upper de Primeknit que se adapta perfectamente al pie. Suela FG con tacos optimizados para césped natural. Incluyen plantilla extraíble OrthoLite para mayor comodidad.', '/imagenes/productos/productosImg.jpg', 119.95, 15, 1);

INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8436010843403', 'Portería Aluminio Reglamentaria', 'Portería de fútbol 11 reglamentaria fabricada en aluminio anodizado de 100x100mm. Medidas oficiales 7,32x2,44m. Red incluida de polipropileno de alta resistencia. Sistema de anclaje al suelo homologado por la FIFA. Desmontable para facilitar su almacenamiento y transporte.', '/imagenes/productos/productosImg.jpg', 899.00, 0, 3);

INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8412345678902', 'Camiseta Selección Española 2024', 'Camiseta oficial de la Selección Española de Fútbol temporada 2024. Fabricada con tecnología HEAT.RDY para mantener al jugador fresco. Tejido reciclado AEROREADY de secado rápido. Escudo bordado y detalles en rojo y amarillo. Disponible en tallas S a XXL.', '/imagenes/productos/productosImg.jpg', 89.95, 20, 1);

INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('4006381333931', 'Espinilleras Pro Carbon', 'Espinilleras profesionales con carcasa exterior de fibra de carbono ultraligera. Relleno interior de espuma EVA para absorción máxima de impactos. Sistema de ajuste con malla transpirable. Homologadas para competición oficial. Protección superior sin sacrificar movilidad.', '/imagenes/productos/productosImg.jpg', 45.00, 5, 2);

INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8411329045671', 'Balón Liga Santander', 'Balón oficial de la Liga Santander temporada 2024-25. Fabricado en cuero sintético de alta calidad con 32 paneles cosidos a mano. Cámara de butilo de alta presión. Certificado FIFA Basic.', '/imagenes/productos/productosImg.jpg', 24.99, 0, 3);

-- Producto sin categorías (situación 3)
INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8419876543210', 'Guantes de Portero Elite', 'Guantes profesionales de portero con palma de látex natural de alta adherencia. Corte negativo para mayor ajuste. Cierre con velcro reforzado. Protección con varillas en los dedos extraíbles.', '/imagenes/productos/productosImg.jpg', 59.99, 0, 2);

-- 4. RELACIONES PRODUCTO-CATEGORÍA
-- producto_id: 1=Balón Champions, 2=Botas Predator, 3=Portería, 4=Camiseta, 5=Espinilleras, 6=Balón Liga, 7=Guantes
-- categoria_id: 1=Balones, 2=Botas, 3=Equipaciones, 4=Protecciones, 5=Porterías, 6=Accesorios

-- Balón Champions → solo en Balones (situación 4: solo una categoría)
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (1, 1);

-- Botas Predator → Botas (situación 4)
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (2, 2);

-- Portería → Porterías (situación 4)
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (3, 5);

-- Camiseta → Equipaciones Y Accesorios (situación 5: varias categorías)
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (4, 3);
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (4, 6);

-- Espinilleras → Protecciones Y Accesorios (situación 5: varias categorías)
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (5, 4);
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (5, 6);

-- Balón Liga → Balones (situación 7: categoría con varios productos — junto al Balón Champions)
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (6, 1);

-- Guantes → sin categorías (situación 3: ninguna fila aquí)

-- 5. USUARIO INICIAL DE ADMINISTRACIÓN
-- Email:      admin@tienda.com
-- Password:   Password   (codificada con bcrypt, cost factor 12)
INSERT INTO usuario (
    email,
    nombre,
    apellidos,
    telefono,
    fecha_nacimiento,
    password,
    fecha_registro
) VALUES (
             'admin@tienda.com',
             'Administrador',
             'de la Tienda',
             NULL,
             NULL,
             '$2a$12$Ing1AlDL6NeFF/9r8/Ott.uGau50A/7rHjoyvehYNen4rxREk8//W',
             CURRENT_TIMESTAMP
         );
-- 6. ROLES
-- Los identificadores son strings cortos que coinciden con la convención
-- de Spring Security (los pasaremos a "ROLE_" + id en getAuthorities()).
INSERT INTO rol (id, descripcion) VALUES ('USER',  'Usuario normal');
INSERT INTO rol (id, descripcion) VALUES ('ADMIN', 'Administrador');

-- 7. USUARIO USER NORMAL
-- Email:    user@tienda.com
-- Password: Password   (codificada con bcrypt, cost factor 12, hash distinto al del admin)
INSERT INTO usuario (
    email,
    nombre,
    apellidos,
    telefono,
    fecha_nacimiento,
    password,
    fecha_registro
) VALUES (
             'user@tienda.com',
             'Usuario',
             'Normal',
             NULL,
             NULL,
             '$2a$12$bckWCZwFjPHiA6VAs2TXZuMGf2hMukK6tnkE/ynW13FjJKogpTbjS',
             CURRENT_TIMESTAMP
         );

-- 8. ASIGNACIÓN DE ROLES A USUARIOS
-- Asumimos que admin@tienda.com tiene id=1 (es el primer INSERT en usuario)
-- y user@tienda.com tiene id=2 (segundo INSERT). Esto se cumple porque la
-- columna usuario.id es IDENTITY y los inserts van en este orden.

-- admin@tienda.com → ADMIN + USER
-- (un admin SIGUE siendo un usuario; tener ambos roles es la convención
--  habitual en RBAC para que el admin pueda usar funcionalidades de USER)
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 'ADMIN');
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 'USER');

-- user@tienda.com → solo USER
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (2, 'USER');