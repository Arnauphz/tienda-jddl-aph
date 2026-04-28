-- INSERT INTO categoria (nombre, descripcion, imagen) VALUES
--                                                         ('Equipaciones', 'Ropa de futbol', 'imagenCat.jpg'),
--                                                         ('Balones', 'Balones para jugar al deporte rey', 'imagenCat.jpg'),
--                                                         ('Accesorios', 'Material para mejorar el rendimiento', 'imagenCat.jpg');
--
-- -- Productos con marca e imagen
-- -- Productos con marca e imagen
-- INSERT INTO producto (codigo, nombre, marca, descripcion, imagen, precio, descuento) VALUES
--                                                                                          ('8410013010513', 'Balón de Fútbol Champions League', 'Adidas', 'Balón oficial de la UEFA Champions League, fabricado con paneles termosoldados para una superficie perfectamente lisa. Su núcleo de butilo garantiza una retención del aire excepcional. Diseñado para partidos de alta competición en césped natural y artificial. El preferido por los mejores jugadores del mundo.', '/images/productos/balon-champions.jpg', 34.99, 10),
--                                                                                          ('8480017358073', 'Botas de Fútbol Predator', 'Adidas', 'Botas de fútbol con tecnología Predator para un control y potencia de disparo superiores. Upper de Primeknit que se adapta perfectamente al pie. Suela FG con tacos optimizados para césped natural. Incluyen plantilla extraíble OrthoLite para mayor comodidad.', '/images/productos/botas-predator.jpg', 119.95, 15),
--                                                                                          ('8436010843403', 'Portería de Fútbol 11 Aluminio', 'Joma', 'Portería de fútbol 11 reglamentaria fabricada en aluminio anodizado de 100x100mm. Medidas oficiales 7,32x2,44m. Red incluida de polipropileno de alta resistencia. Sistema de anclaje al suelo homologado por la FIFA. Desmontable para facilitar su almacenamiento y transporte.', '/images/productos/porteria-aluminio.jpg', 899.00, 0),
--                                                                                          ('8412345678902', 'Camiseta Selección Española 2024', 'Adidas', 'Camiseta oficial de la Selección Española de Fútbol temporada 2024. Fabricada con tecnología HEAT.RDY para mantener al jugador fresco. Tejido reciclado AEROREADY de secado rápido. Escudo bordado y detalles en rojo y amarillo. Disponible en tallas S a XXL.', '/images/productos/camiseta-seleccion.jpg', 89.95, 20),
--                                                                                          ('4006381333931', 'Espinilleras de Fútbol Pro Carbon', 'Nike', 'Espinilleras profesionales con carcasa exterior de fibra de carbono ultraligera. Relleno interior de espuma EVA para absorción máxima de impactos. Sistema de ajuste con malla transpirable. Homologadas para competición oficial. Protección superior sin sacrificar movilidad.', '/images/productos/espinilleras-carbon.jpg', 45.00, 5);
--
-- -- Productos con marca pero sin imagen
-- INSERT INTO producto (codigo, nombre, marca, descripcion, imagen, precio, descuento) VALUES
--                                                                                          ('5010123456789', 'Guantes de Portero Profesional', 'Reusch', 'Guantes de portero con palma de látex negativo de alta adherencia en condiciones secas y húmedas. Corte negativo para mejor contacto con el balón. Protección en los dedos con varillas extraíbles. Cierre de velcro con logotipo reflectante. Ideales para porteros de categorías profesionales y semiprofesionales.', NULL, 59.99, 10),
--                                                                                          ('3017620422003', 'Balón de Fútbol Sala FIFA Pro', 'Select', 'Balón de fútbol sala con certificación FIFA Pro. Cosido a mano con cuero sintético de 4 capas para mayor durabilidad. Cámara interior de butilo para una presión constante. Su bajo rebote facilita el control en superficies duras. El estándar en competiciones nacionales e internacionales de fútbol sala.', NULL, 49.95, 0),
--                                                                                          ('0012345678905', 'Malla Portátil de Fútbol 7', 'Powershot', 'Malla portátil para portería de fútbol 7. Fabricada en polipropileno de alta resistencia a los rayos UV. Sistema de montaje rápido sin necesidad de herramientas. Compatible con porterías de aluminio y PVC estándar. Incluye bolsa de transporte y sistema de sujeción al suelo.', NULL, 39.00, 0),
--                                                                                          ('8888888888884', 'Peto de Entrenamiento Fútbol Pack 14', 'Kipsta', 'Pack de 14 petos de entrenamiento en dos colores (7 amarillos y 7 rojos) para diferenciar equipos. Fabricados en poliéster ligero y transpirable. Talla única con sistema de apertura lateral para facilitar su colocación. Resistentes al lavado y al uso continuado en entrenamientos.', NULL, 24.99, 0),
--                                                                                          ('7501031311309', 'Chaqueta de Chándal Técnica', 'Nike', 'Chaqueta de chándal de fútbol con tecnología Dri-FIT para evacuar el sudor. Cuello alto con cremallera completa. Bolsillos laterales con cremallera. Puños y bajo elásticos para mayor comodidad. Ideal para calentamiento y entrenamiento en condiciones frías.', NULL, 64.95, 12);
--
-- -- Productos sin marca pero con imagen
-- INSERT INTO producto (codigo, nombre, marca, descripcion, imagen, precio, descuento) VALUES
--                                                                                          ('4007817328238', 'Red de Portería de Repuesto', NULL, 'Red de repuesto para portería de fútbol 11 reglamentaria. Fabricada en polipropileno trenzado de 3mm resistente a la intemperie. Medidas oficiales 7,32x2,44m con profundidad de 1,5m. Incluye cordón de sujeción. Compatible con la mayoría de porterías de aluminio del mercado.', '/images/productos/red-porteria.jpg', 29.99, 0),
--                                                                                          ('5449000000996', 'Cronómetro de Árbitro Digital', NULL, 'Cronómetro digital profesional para árbitros de fútbol. Resistente al agua y a los golpes. Funciones de cuenta atrás, tiempo de descuento y memoria de tiempos parciales. Pantalla LCD grande con retroiluminación. Incluye cordón y funda protectora de silicona.', '/images/productos/cronometro-arbitro.jpg', 18.50, 25),
--                                                                                          ('8001505005592', 'Banderines de Córner Pack 4', NULL, 'Pack de 4 banderines de córner reglamentarios para campo de fútbol. Poste flexible de fibra de vidrio de 1,50m de altura. Banderín de poliéster resistente al viento en color amarillo fluorescente. Base de plástico con punta para clavar en el césped. Homologados para competición oficial.', '/images/productos/banderines-corner.jpg', 22.00, 0),
--                                                                                          ('3045140105502', 'Colchoneta de Estiramientos', NULL, 'Colchoneta de estiramientos y calentamiento para vestuarios y zonas de recuperación. Fabricada en espuma de alta densidad de 10cm. Recubierta de vinilo fácil de limpiar y desinfectar. Medidas 180x60cm. Ideal para trabajo de flexibilidad y recuperación muscular post partido.', '/images/productos/colchoneta-estiramientos.jpg', 35.00, 0);
--
-- -- Productos sin marca y sin imagen
-- INSERT INTO producto (codigo, nombre, marca, descripcion, imagen, precio, descuento) VALUES
--                                                                                          ('5000112548167', 'Conos de Entrenamiento Pack 20', NULL, 'Pack de 20 conos de entrenamiento en colores variados para ejercicios de velocidad, agilidad y tácticos.', NULL, 9.99, 0),
--                                                                                          ('8410700624307', 'Silbato de Árbitro', NULL, 'Silbato profesional de metal con bola interior para máxima potencia sonora en partidos oficiales.', NULL, 3.99, 0),
--                                                                                          ('4902430144575', 'Botella de Agua 750ml', NULL, 'Botella deportiva de plástico libre de BPA con boquilla ergonómica. Apta para lavavajillas. Capacidad 750ml.', NULL, 7.95, 0),
--                                                                                          ('3229820129488', 'Cinta de Marcar Campo', NULL, 'Cinta de cal en polvo para marcar líneas en campos de fútbol de tierra y césped natural. Bote de 5kg. De fácil aplicación con marcadora estándar. No daña el césped y es resistente a la lluvia durante varios días tras su aplicación.', NULL, 12.50, 0),
--                                                                                          ('5060337503796', 'Bomba de Hinchar Balones', NULL, 'Bomba de hinchar balones de doble acción con manómetro incorporado. Incluye 3 agujas de recambio y boquilla adaptadora. Cuerpo de aluminio ligero y resistente. Permite inflar el balón a la presión exacta recomendada por los fabricantes.', NULL, 8.99, 0),
--                                                                                          ('8718951189706', 'Marcadores de Posición Planos Pack 10', NULL, 'Pack de 10 marcadores planos de colores para delimitar zonas en ejercicios tácticos de fútbol. Fabricados en plástico flexible irrompible. Fácilmente visibles desde cualquier ángulo. Apilables para un almacenamiento compacto en el bolso del entrenador.', NULL, 6.50, 0);


-- 1. MARCAS
INSERT INTO marca (nombre) VALUES ('Adidas');
INSERT INTO marca (nombre) VALUES ('Nike');
INSERT INTO marca (nombre) VALUES ('Joma');
INSERT INTO marca (nombre) VALUES ('Puma');   -- Marca sin productos (situación 2)

-- 2. CATEGORÍAS
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Balones', 'Balones de fútbol de todas las categorías', null);
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Botas', 'Calzado técnico para fútbol', null);
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Equipaciones', 'Camisetas y conjuntos oficiales', null);
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Protecciones', 'Espinilleras y protecciones', null);
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Porterías', 'Porterías y accesorios de portero', null);
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Accesorios', 'Accesorios varios de fútbol', null); -- Categoría sin productos (situación 6)

-- 3. PRODUCTOS (marca_id en vez de marca)
-- Adidas = 1, Nike = 2, Joma = 3
INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8410013010513', 'Balón Champions League', 'Balón oficial de la UEFA Champions League, fabricado con paneles termosoldados para una superficie perfectamente lisa. Su núcleo de butilo garantiza una retención del aire excepcional. Diseñado para partidos de alta competición en césped natural y artificial. El preferido por los mejores jugadores del mundo.', null, 34.99, 10, 1);

INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8480017358073', 'Botas Predator Elite', 'Botas de fútbol con tecnología Predator para un control y potencia de disparo superiores. Upper de Primeknit que se adapta perfectamente al pie. Suela FG con tacos optimizados para césped natural. Incluyen plantilla extraíble OrthoLite para mayor comodidad.', null, 119.95, 15, 1);

INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8436010843403', 'Portería Aluminio Reglamentaria', 'Portería de fútbol 11 reglamentaria fabricada en aluminio anodizado de 100x100mm. Medidas oficiales 7,32x2,44m. Red incluida de polipropileno de alta resistencia. Sistema de anclaje al suelo homologado por la FIFA. Desmontable para facilitar su almacenamiento y transporte.', null, 899.00, 0, 3);

INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8412345678902', 'Camiseta Selección Española 2024', 'Camiseta oficial de la Selección Española de Fútbol temporada 2024. Fabricada con tecnología HEAT.RDY para mantener al jugador fresco. Tejido reciclado AEROREADY de secado rápido. Escudo bordado y detalles en rojo y amarillo. Disponible en tallas S a XXL.', null, 89.95, 20, 1);

INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('4006381333931', 'Espinilleras Pro Carbon', 'Espinilleras profesionales con carcasa exterior de fibra de carbono ultraligera. Relleno interior de espuma EVA para absorción máxima de impactos. Sistema de ajuste con malla transpirable. Homologadas para competición oficial. Protección superior sin sacrificar movilidad.', null, 45.00, 5, 2);

INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8411329045671', 'Balón Liga Santander', 'Balón oficial de la Liga Santander temporada 2024-25. Fabricado en cuero sintético de alta calidad con 32 paneles cosidos a mano. Cámara de butilo de alta presión. Certificado FIFA Basic.', null, 24.99, 0, 3);

-- Producto sin categorías (situación 3)
INSERT INTO producto (codigo, nombre, descripcion, imagen, precio, descuento, marca_id)
VALUES ('8419876543210', 'Guantes de Portero Elite', 'Guantes profesionales de portero con palma de látex natural de alta adherencia. Corte negativo para mayor ajuste. Cierre con velcro reforzado. Protección con varillas en los dedos extraíbles.', null, 59.99, 0, 2);

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