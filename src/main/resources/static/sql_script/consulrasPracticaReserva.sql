use rentCart01;
SELECT * from reserva;
-- drop table rentCart01;

/* encontrar algo repetido por columnas*/
--SELECT telefono FROM usuarios
--     GROUP BY telefono
--     HAVING COUNT(*)>1;

--
--INSERT INTO `reserva`
--(`fecha_reserva`, `fecha_retiro`, `fecha_entrega`,`estado_reserva`, `fk_cliente`, `datos_vehiculo_id`)
--VALUES
--('2023-01-02', '2023-02-01', '2023-02-05',1, 1002, 3009),
--('2023-01-02', '2023-02-24', '2023-02-05',1, 1002, 3002),
--('2023-01-02', '2023-02-09', '2023-02-12',1, 1002, 3002),
--('2023-01-02', '2023-02-13', '2023-02-15',1, 1002, 3009),
--('2023-01-02', '2023-02-23', '2023-02-26',1, 1002, 3002),
--('2023-01-02', '2023-02-22', '2023-02-26',1, 1002, 3009);

select datos_vehiculo_id as idv, id as idr, fecha_retiro, fecha_entrega from reserva ORDER by datos_vehiculo_id;

select datos_vehiculo_id as idv, id as idr, fecha_retiro, fecha_entrega from reserva where datos_vehiculo_id = 3007 ;

select c.modelo, c.marca from reserva r 
inner join vehiculo c on r.datos_vehiculo_id = c.id;

/*autos que tienen reserva */
select DISTINCT c.id from reserva r 
inner join vehiculo c on r.datos_vehiculo_id = c.id
where r.datos_vehiculo_id = c.id;

/*autos que tienen reserva entre fechas de RESERVA !!!*/
select DISTINCT c.id from reserva r 
inner join vehiculo c on r.datos_vehiculo_id = c.id
where r.datos_vehiculo_id = c.id and r.fecha_reserva between '2023-01-29' AND '2023-02-02';

/*autos que tienen reserva entre fechas de RETIRO !!!*/
select DISTINCT c.id from reserva r 
inner join vehiculo c on r.datos_vehiculo_id = c.id
where r.datos_vehiculo_id = c.id and r.fecha_retiro between '2023-02-24' AND '2023-02-25';

/*autos que NO!!!!!! tienen reserva entre fechas de RETIRO !!!*/
SELECT v.id FROM vehiculo v WHERE v.id NOT IN (
select  v.id from reserva r 
inner join vehiculo v on r.datos_vehiculo_id = v.id
where  r.fecha_retiro between '2023-02-24' AND '2023-02-25');


/*autos que NO!!!!!! tienen reserva entre fechas de RETIRO !!!*/
SELECT v.id FROM vehiculo v WHERE v.id NOT IN (
select  v.id from reserva r 
inner join vehiculo v on r.datos_vehiculo_id = v.id
where  r.fecha_retiro between '2023-02-24' AND '2023-02-25' or r.fecha_entrega between '2023-02-24' AND '2023-02-25');

/*autos que tienen reserva entre fechas de RETIRO !!!*/
select DISTINCT c.id from reserva r 
inner join vehiculo c on r.datos_vehiculo_id = c.id
where r.datos_vehiculo_id = c.id and r.fecha_retiro between '2023-02-24' AND '2023-02-25';


select 'aca';
/*autos que NO!!! tienen reserva entre fechas de RETIRO y  DEVOLUCION !!!*/
SELECT v.id FROM vehiculo v WHERE v.id  IN (
select  v.id from reserva r 
INNER join vehiculo v on r.datos_vehiculo_id = v.id
where  r.fecha_retiro between '2023-02-01' AND '2023-02-06' or r.fecha_entrega between '2023-02-01' AND '2023-02-06') ;


/*autos. RESERVA DENTRO DE UNA RESERVA !!!*/
SELECT v.id FROM vehiculo v WHERE v.id IN(
select  v.id from reserva r 
INNER join vehiculo v on r.datos_vehiculo_id = v.id
where  r.fecha_retiro < '2023-02-03' AND r.fecha_entrega > '2023-02-04'
) ;





SELECT v.id FROM vehiculo v WHERE v.id NOT IN (
select  v.id from reserva r 
INNER join vehiculo v on r.datos_vehiculo_id = v.id
where  r.fecha_retiro between '2023-02-01' AND '2023-02-06' or r.fecha_entrega between '2023-02-01' AND '2023-02-06'
 )AND (SELECT v.id FROM vehiculo v WHERE v.id IN(
select  v.id from reserva r 
INNER join vehiculo v on r.datos_vehiculo_id = v.id
where  r.fecha_retiro < '2023-02-01' AND r.fecha_entrega > '2023-02-06'));

-- SELECT r.datosVehiculo FROM ReservaWeb r WHERE NOT 
-- (r.fechaEntrega >= :checkin AND r.fechaEntrega <= :checkout AND 
-- r.fechaRetiro >= :checkin AND r.fechaRetiro <= :checkout AND
--  r.fechaRetiro <= :checkin AND r.fechaEntrega >= :checkout  )";


-- where  r.fecha_retiro < 
-- '2023-02-07' and     '2023-02-10'

-- '2023-02-10' and     '2023-02-11'

-- '2023-02-10' and     '2023-02-14'

-- '2023-02-16' and     '2023-02-18'

-- where  r.fecha_retiro < '2023-02-10' OR r.fecha_entrega > '2023-02-11'

-- where  r.fecha_retiro < '2023-02-02' OR r.fecha_entrega > '2023-02-14'


-- esta funciona pero no en el caso 4
select 'vamos!!!';

SELECT v.id FROM vehiculo v WHERE v.id  IN (
select  v.id from reserva r 
INNER join vehiculo v on r.datos_vehiculo_id = v.id
where  r.fecha_retiro between 
'2023-02-02' and     '2023-02-03'
    or r.fecha_entrega between 
'2023-02-02' and     '2023-02-03'
) ; 

    
-- esta funciona para el caso 4
select 'vamos!!!';
SELECT v.id FROM vehiculo v WHERE v.id IN(
select  v.id from reserva r 
INNER join vehiculo v on r.datos_vehiculo_id = v.id
where  r.fecha_retiro < '2023-02-02' AND r.fecha_entrega > '2023-02-03');