show databases;
use rentCart01;
show tables;
SELECT `fecha_retiro`,`fecha_entrega` FROM `reserva`;   

SELECT id,`fecha_retiro`,`fecha_entrega` FROM `reserva` where fecha_retiro < '2023-02-12' and fecha_entrega >'2023-02-11';
select 'arranca';
SELECT `fecha_retiro`,`fecha_entrega`, datos_vehiculo_id FROM `reserva` where datos_vehiculo_id = 105;

/*insertar un registro en la tabla cliente*/
/* INSERT INTO `reserva`
-- (`estado_reserva`, `fecha_entrega`, `fecha_reserva`, `fecha_retiro`, `fk_cliente`, `datos_vehiculo_id`) 
-- VALUES ('1','2023-04-22','2023-04-02','2023-04-03','120','107');
*/


/*todo lo que esta en las dos tablas entre fechas */
    SELECT * FROM reserva r
    RIGHT JOIN vehiculo v on r.datos_vehiculo_id = v.id
    WHERE fecha_retiro  BETWEEN '2022-05-01' and '2023-05-15'  
    ORDER BY `r`.`fecha_retiro` DESC;

SELECT count(id) from vehiculo;

/*--trae todos los id vehiculos que tienen reserva
-- por defecto de manera ascendente y repetidos*/
select r.datos_vehiculo_id from reserva r
inner join vehiculo v on r.datos_vehiculo_id = v.id;

/* id vehiculos que tengan reserva sin repetir */
select  DISTINCT r.datos_vehiculo_id from reserva r
inner join vehiculo v on r.datos_vehiculo_id = v.id;

/*todos los id vehiculos que tienen reserva entre fechas */
select r.datos_vehiculo_id from reserva r
inner join vehiculo v on r.datos_vehiculo_id = v.id
where r.fecha_retiro between '2021-02-12' and '2024-02-11';



select r.datos_vehiculo_id from reserva r
left join vehiculo v on r.datos_vehiculo_id = v.id
where r.fecha_retiro between '2021-02-12' and '2024-02-11';


/*todo lo que hay entre reserva y vehiculo*/
select * from reserva r
INNER join vehiculo v on r.datos_vehiculo_id = v.id;

/*todos los id vehiculos que tienen reservas repetidos*/
select v.id from reserva r
RIGHT join vehiculo v on r.datos_vehiculo_id = v.id ;

/*todos los id vehiculos que no tienen reserva*/
SELECT v.id FROM vehiculo v
RIGHT JOIN reserva r on r.datos_vehiculo_id = v.id ;


/*todos los iv vehiculos que no tiene reserva */
select v.id, r.id  from vehiculo v
LEFT join reserva r on r.datos_vehiculo_id = v.id
WHERE r.id is null;



/*

seguir con esta 
*/
/*todos los id vehiculos que no tiene reserva entre fechas*/
SELECT * FROM vehiculo v WHERE v.id NOT IN (
SELECT `datos_vehiculo_id` FROM reserva 
WHERE fecha_retiro BETWEEN '2023-02-01' and '2023-02-15' 
OR fecha_entrega BETWEEN '2023-02-01' and '2023-02-23');

/*las fechas reservadas segun el id vehiculo*/
SELECT fecha_retiro, fecha_entrega FROM `reserva`r WHERE r.`datos_vehiculo_id`= 3005;


/*CLIENTE*/
/*LISTAR TODOS LOS CLIENTES QUE TENGAN RESERVA*/
SELECT r.fk_cliente FROM reserva r
INNER JOIN cliente c ON r.fk_cliente = c.id;
/*LISTAR TODOS LOS CLIENTES QUE TENGAN RESERVA  fechas de reserva*/
SELECT r.id,r.fk_cliente, r.fecha_reserva FROM reserva r
INNER JOIN cliente c ON r.fk_cliente = c.id
where r.fecha_reserva between '2023-02-01' and '2023-02-28';
