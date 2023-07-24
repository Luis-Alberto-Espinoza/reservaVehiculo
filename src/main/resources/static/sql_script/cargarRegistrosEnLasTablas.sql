/* 

Todo a minúsculas en MySQL:
UPDATE tabla SET campo=LOWER(campo)

Todo a mayúsculas en MySQL:
UPDATE tabla SET campo=UPPER(campo)

*/
CREATE SCHEMA IF NOT EXISTS `rentCart01` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

use  rentCart01 ;



/*creacion de la tabla cliente */
CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `dni` bigint NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` bigint NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `clave1` varchar(255) DEFAULT NULL,
  `alta` DATE DEFAULT NULL,
  `baja` DATE DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;


/*ingresar valores de manera masiva */

INSERT INTO `cliente` (`id`, `nombre`, `apellido`, `fecha_nacimiento`, `dni`, `direccion`, `telefono`, `mail`, `clave1`, `alta`, `baja`) VALUES
(1001, 'joaquin', 'rios', '2000-01-11', 1233208, 'calle 123', 7896544, 'correoempleado_23@correo.com', '123456', '2001-01-11', NULL);



  /*se ingresa el resto de los registros sin el ID ya que no hace falta porque es autoincremental*/
  INSERT INTO `cliente`
  (`nombre`,`apellido`,`fecha_nacimiento`,`dni`,`direccion`,`telefono`,`mail`,`clave1`,`alta`,`baja`) 
  VALUES
('albert', 'espi', '1947-02-11', 1234345678, 'sarratea 7053', 261399, 'acorreo@correo.com', '123456', '2000-01-11', NULL),
('josé manuel', 'martinez', '1964-08-22', 7896555, 'calle 134', 1233222, 'correocliente_11@correo.com', '123456', '2000-01-14', NULL),
('laurent serra', 'cambiado', '1959-07-29', 7896560, 'calle 139', 1233227, 'correocliente_16@correo.com', '123456', '2000-01-11', NULL),
('juan carlos', 'ortiz', '1986-12-25', 7896549, 'calle 128', 1233216, 'correocliente_05@correo.com', '123456', '2000-03-11', NULL),
('luis', 'espinoza', '1977-07-19', 29148852, 'san martin 2324', 888558855, 'lcorreo@correo.com', '123456', '2003-01-11', NULL),
('diego', 'maradona', '1947-02-11', 198645, 'argentina 86', 12521, 'correo02@cliente.com', '123456', '2000-01-21', NULL),
('esteban', 'gardening', '1977-07-19', 55578845, 'amigorena 39', 12765521, 'correo03@cliente.com', '123456', '2000-01-31', NULL),
('marcos', 'magaña', '1996-04-26', 7896544, 'calle 123', 1233211, 'correocliente_00@correo.com', '123456', '2000-02-21', NULL),
('ruben', 'lópez', '1998-08-08', 7896545, 'calle 124', 1233212, 'correocliente_01@correo.com', '123456', '2000-03-13', NULL),
('alberto', 'soria', '1947-02-11', 7896546, 'calle 125', 1233213, 'correocliente_02@correo.com', '123456', '2000-04-14', NULL),
('maria', 'solís', '1960-01-31', 7896547, 'calle 126', 1233214, 'correocliente_03@correo.com', '123456', '2000-05-15', NULL),
('felipe', 'rosas', '1964-08-22', 7896548, 'calle 127', 1233215, 'correocliente_04@correo.com', '123456', '2000-04-15', NULL),
('carlos', 'soria', '1959-07-29', 7896550, 'calle 129', 1233217, 'correocliente_06@correo.com', '123456', '2000-03-18', NULL),
('mariano', 'lópez', '1986-12-25', 7896551, 'calle 130', 1233218, 'correocliente_07@correo.com', '123456', '2000-11-15', NULL),
('lucio', 'campoamor', '1977-07-19', 7896552, 'calle 131', 1233219, 'correocliente_08@correo.com', '123456', '2003-01-31', NULL),
('hilario', 'rodriguez', '1996-04-26', 7896553, 'calle 132', 1233220, 'correocliente_09@correo.com', '123456', '2002-08-19', NULL),
('emmanuel', 'magaña', '1998-08-08', 7896554, 'calle 133', 1233221, 'correocliente_10@correo.com', '123456', '2000-12-15', NULL),
('david', 'palma', '1960-01-31', 7896556, 'calle 135', 1233223, 'correocliente_12@correo.com', '123456', '2010-10-10', NULL),
('oscar', 'palma', '1964-08-22', 7896557, 'calle 136', 1233224, 'correocliente_13@correo.com', '123456', '2001-12-13', NULL),
('francois', 'fignon', '1959-07-29', 7896558, 'calle 137', 1233225, 'correocliente_14@correo.com', '123456', '2000-01-01', NULL),
('lionel', 'narvaez', '1986-12-25', 7896559, 'calle 138', 1233226, 'correocliente_15@correo.com', '123456', '2000-01-05', NULL),
('michael', 'bolton', '1992-01-09', 7896561, 'calle 140', 1233228, 'correocliente_17@correo.com', '123456', '2000-09-11', NULL),
('hilary', 'sanchez', '1997-08-14', 7896562, 'calle 141', 1233229, 'correocliente_18@correo.com', '123456', '2000-08-11', NULL),
('marcus', 'washington', '1999-09-09', 7896563, 'calle 142', 1233230, 'correocliente_19@correo.com', '123456', '2000-07-11', NULL),
('lorena', 'paxton', '1997-02-25', 7896564, 'calle 143', 1233231, 'correocliente_20@correo.com', '123456', '2000-06-21', NULL),
('nei', 'paxton', '2001-12-11', 7896565, 'calle 144', 1233232, 'correocliente_21@correo.com', '123456', '2003-07-19', NULL),
('narumi', 'nishikori', '2004-08-25', 7896566, 'calle 145', 1233233, 'correocliente_22@correo.com', '123456', '2005-12-14', NULL),
('takuma', 'riko', '2003-09-25', 7896567, 'calle 146', 1233234, 'correocliente_23@correo.com', '123456', '2001-11-11', NULL);



/*poner estos campos en minusculas en la base de datos */
UPDATE cliente SET nombre=LOWER(nombre);
UPDATE cliente SET apellido=LOWER(apellido);
UPDATE cliente SET direccion=LOWER(direccion);
UPDATE cliente SET mail=LOWER(mail);


/*##################################################################################################*/



/*TABLA - EMPLEADO*/
/*CREACION DE LA TABLA EMPLEADOS */
CREATE TABLE `empleado` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `dni` bigint NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` bigint NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `clave1` varchar(255) DEFAULT NULL,
  `alta` DATE DEFAULT NULL,
  `baja` DATE DEFAULT NULL,
  `type_empleado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


/*ingresar valores de manera masiva */

INSERT INTO `empleado`
(`id`, `nombre`, `apellido`, `fecha_nacimiento`, `dni`, `direccion`, `telefono`, `mail`, `clave1`, `alta`, `baja`, `type_empleado`) 
VALUES
(2001, 'manuel', 'belgrano', '1952-05-25', 1233208, 'calle 123', 7896544, 'correoempleado_2253@correo.com', '123456', NULL, NULL, 'gerente');


/*se ingresa el resto de los registros sin el ID ya que no hace falta porque es autoincremental*/

/**/
INSERT INTO `empleado`
(`nombre`, `apellido`, `fecha_nacimiento`, `dni`, `direccion`, `telefono`, `mail`, `clave1`, `alta`, `baja`, `type_empleado`) 
VALUES

('ediberto', 'britos', '1980-01-25', 1233209, 'calle 174125', 78988544, 'correoempleado_20@correo.com', '123456', '2001-01-11', NULL, 'gerente'),
('ariel', 'bazan', '1993-09-25', 1233210, 'calle 965874', 7899544, 'correoempleado_21@correo.com', '123456', '2002-11-11', NULL, 'gerente'),
('daniel', 'casas', '1988-06-01', 1233211, 'calle numero 4234', 7833544, 'correoempleado_22@correo.com', '123456', '2003-11-11', NULL, 'gerente'),
('antonio', 'solarte', '1977-07-19', 1233212, 'calle 124', 7896545, 'correoempleado_01@correo.com', '123456', '2003-10-10', NULL, 'gerente'),
('victoria', 'ríos', '1996-04-26', 1233223, 'calle 135', 7896556, 'correoempleado_12@correo.com', '123456', '2000-01-01', NULL, 'ventas'),
('paco', 'angulo', '1998-08-08', 1233214, 'calle 126', 7896547, 'correoempleado_03@correo.com', '123456', '2000-01-01', NULL, 'administrador'),
('guillermo', 'roa', '1947-02-11', 1233215, 'calle 127', 7896548, 'correoempleado_04@correo.com', '123456', '2000-01-01', NULL, 'gerente'),
('david', 'lópez', '1960-01-31', 1233216, 'calle 128', 7896549, 'correoempleado_05@correo.com', '123456', '2000-01-01', NULL, 'ventas'),
('jose', 'giraldo', '1964-08-22', 1233217, 'calle 129', 7896550, 'correoempleado_06@correo.com', '123456', '2001-10-10', NULL, 'ventas'),
('pedro', 'rojas', '1959-07-29', 1233218, 'calle 130', 7896551, 'correoempleado_07@correo.com', '123456', '2000-08-09', NULL, 'ventas'),
('juan', 'rozo', '1986-12-25', 1233219, 'calle 131', 7896552, 'correoempleado_08@correo.com', '123456', '2002-11-11', NULL, 'ventas'),
('javier', 'blanco', '1992-01-09', 1233220, 'calle 132', 7896553, 'correoempleado_09@correo.com', '123456', '2010-01-01', NULL, 'ventas'),
('maria', 'pulido', '1997-08-14', 1233221, 'calle 133', 7896554, 'correoempleado_10@correo.com', '123456', '2010-05-19', NULL, 'ventas'),
('beatriz', 'moreno', '1999-09-09', 1233222, 'calle 134', 7896555, 'correoempleado_11@correo.com', '123456', '2012-09-25', NULL, 'ventas'),
('luis', 'muñoz', '1997-02-25', 1233224, 'calle 136', 7896557, 'correoempleado_13@correo.com', '123456', '2014-03-11', NULL, 'ventas'),
('mario', 'gómez', '2001-12-11', 1233225, 'calle 137', 7896558, 'correoempleado_14@correo.com', '123456', '2015-10-17', NULL, 'ventas'),
('cristian', 'rojas', '2004-08-25', 1233226, 'calle 138', 7896559, 'correoempleado_15@correo.com', '123456', '2015-12-11', NULL, 'ventas'),
('francisco', 'pérez', '2003-09-25', 1233227, 'calle 139', 7896560, 'correoempleado_16@correo.com', '123456', '2016-03-18', NULL, 'ventas'),
('maria', 'alfonso', '2000-01-01', 1233228, 'calle 140', 7896561, 'correoempleado_17@correo.com', '123456', '2016-07-08', NULL, 'ventas'),
('federico', 'mora', '2003-06-09', 1233229, 'calle 141', 7896562, 'correoempleado_18@correo.com', '123456', '2017-04-06', NULL, 'ventas'),
('jose', 'díaz', '2000-09-10', 1233213, 'calle 125', 7896546, 'correoempleado_02@correo.com', '123456', '2018-11-11', NULL, 'administrador');



/*poner estos campos en minusculas en la base de datos */
UPDATE empleado SET nombre=LOWER(nombre);
UPDATE empleado SET apellido=LOWER(apellido);
UPDATE empleado SET direccion=LOWER(direccion);
UPDATE empleado SET mail=LOWER(mail);
UPDATE empleado SET type_empleado=LOWER(type_empleado);


/*##################################################################################################*/

/*CREACION DE LA TABLA VEHICULO*/
CREATE TABLE `vehiculo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `patente` varchar(255) DEFAULT NULL,
  `tipo_vehiculo` varchar(255) DEFAULT NULL,
  `type_gama` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `cilindrada_motor` varchar(255) DEFAULT NULL,
  `combustible` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `vehiculo`
(`id`,`marca`,`modelo`,`patente`,`tipo_vehiculo`,`type_gama`,`color`,`cilindrada_motor`,`combustible`, `precio`)
VALUES


(3001, 'peugeot', '208', 'asd123878797987', 'compacto', 'standar', 'gris', '1,4', 'gas', '1', 1250);

INSERT INTO `vehiculo`
(`marca`,`modelo`,`patente`,`tipo_vehiculo`,`type_gama`,`color`,`cilindrada_motor`,`combustible`, `precio`)
VALUES
('chevrolet', 'cruze rs', 'dss321', 'compacto', 'standar', 'negro', '1,6', 'nafta', '1', 1500),
('peugeot', '208 gt', 'qwe456', 'compacto', 'alta', 'blanco', '2,4', 'gas oil', '1', 2000),
('chevrolet', 'onix', 'ewq654', 'compacto', 'alta', 'azul', '1,4', 'nafta', '1', 2200),
('chevrolet', 'joy', 'rty789', 'compacto', 'alta', 'rojo', '1,6', 'gas', '1', 2150),
('chevrolet', 'spin', 'ytr987', 'utilitario', 'standar', 'blanco', '2,4', 'nafta', '1', 1600),
('peugeot', 'partner', 'oiu123', 'utilitario', 'standar', 'azul', '1,4', 'gas oil', '1', 1350),
('volkswagen', 'polo', 'uio321', 'utilitario', 'standar', 'rojo', '1,6', 'nafta', '1', 1100),
('peugeot', 'expert', 'mnb654', 'utilitario', 'standar', 'blanco', '2,4', 'nafta', '1', 2000),
('peugeot', 'boxer', 'jhg625', 'utilitario', 'standar', 'gris', '1,4', 'gas', '1', 1600),
('chevrolet', 'corsa', 'hgf789', 'familiar', 'standar', 'negro', '1,6', 'nafta', '1', 1800),
('peugeot', '504', 'gfd987', 'familiar', 'standar', 'blanco', '2,4', 'nafta', '1', 1750),
('volkswagen', 'taos', 'fds852', 'minibans', 'standar', 'gris', '1,4', 'gas oil', '1', 2600),
('peugeot', 'boxer minibus', 'asd741', 'minibans', 'standar', 'gris', '1,4', 'gas', '1', 3000),
('volkswagen', 'virtus', 'uyg673', 'familiar', 'alta', 'negro', '1,6', 'nafta', '1', 2900);

/*poner estos campos en minusculas en la base de datos */
UPDATE vehiculo SET marca=LOWER(marca);
UPDATE vehiculo SET modelo=LOWER(modelo);
UPDATE vehiculo SET patente=LOWER(patente);
UPDATE vehiculo SET tipo_vehiculo=LOWER(tipo_vehiculo);
UPDATE vehiculo SET type_gama=LOWER(type_gama);
UPDATE vehiculo SET color=LOWER(color);
UPDATE vehiculo SET cilindrada_motor=LOWER(cilindrada_motor);
UPDATE vehiculo SET combustible=LOWER(combustible);


/*##################################################################################################*/

/*creacion de la tabla reserva*/
CREATE TABLE `reserva` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha_reserva` date DEFAULT NULL,
  `fecha_retiro` date DEFAULT NULL,
  `fecha_entrega` date DEFAULT NULL,
  `estado_reserva` varchar(255) DEFAULT NULL,
  `fk_cliente` bigint NOT NULL,
  `datos_vehiculo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
 
  FOREIGN KEY (`datos_vehiculo_id`) REFERENCES `vehiculo` (`id`),
  FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`id`)
) ;

/*insertar el primer registro para crear el inicio del id para la reserva es la lina del 4 mil*/

INSERT INTO `reserva`
(`id`, `fecha_reserva`, `fecha_retiro`, `fecha_entrega`,`estado_reserva`, `fk_cliente`, `datos_vehiculo_id`)
VALUES 
(4001, '2000-02-02','2000-02-03','2000-02-07',1, 1005, 3007);

INSERT INTO `reserva`
(`fecha_reserva`, `fecha_retiro`, `fecha_entrega`,`estado_reserva`, `fk_cliente`, `datos_vehiculo_id`)
VALUES 
('2023-01-02', '2023-02-03', '2023-02-07',1, 1003, 3002),
('2023-01-28', '2023-01-28', '2023-02-05',1, 1004, 3007),
('2023-01-28', '2023-02-07', '2023-02-12',1, 1001, 3003),
('2023-01-28', '2023-02-12', '2023-02-14',1, 1009, 3008),
('2023-01-28', '2023-02-12', '2023-02-14',1, 1007, 3006);



/*##################################################################################################*/

/*creacion de la tabla contrato*/
CREATE TABLE `contrato` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha_contrato` date DEFAULT NULL,
  `fk_empleado` bigint NOT NULL,
  `fk_reserva` bigint NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`fk_reserva`) REFERENCES `reserva` (`id`),
  FOREIGN KEY (`fk_empleado`) REFERENCES `empleado` (`id`)
) ;

/*ingresar el primer registro pa inicializar el id con el 8001*/
INSERT INTO `contrato`
(`id`, `fecha_contrato`, `fk_empleado`, `fk_reserva`) 
VALUES
(8001, '2023-01-22', 2008, 4001); 
