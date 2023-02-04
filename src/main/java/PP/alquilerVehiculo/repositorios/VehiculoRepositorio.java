package PP.alquilerVehiculo.repositorios;

import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.entidad.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Long> {


    @Query("SELECT v FROM Vehiculo v WHERE v.id NOT IN(SELECT r.datosVehiculo.id FROM ReservaWeb r WHERE r.fechaRetiro BETWEEN :checkin AND :checkout OR r.fechaEntrega BETWEEN :checkin AND :checkout)")
   public List<Vehiculo> xidv_DisponibleFehcas(@Param("checkin") LocalDate fRetiro, @Param("checkout") LocalDate fDevolcion);
//
//    SELECT * FROM vehiculo v WHERE v.id NOT IN (
//            SELECT `datos_vehiculo_id` FROM reserva
//            WHERE fecha_retiro BETWEEN '2023-02-01' and '2023-02-15'
//            OR fecha_entrega BETWEEN '2023-02-01' and '2023-02-23');

    @Query("SELECT r.datosVehiculo FROM ReservaWeb r WHERE NOT (r.fechaEntrega >= :checkin AND r.fechaEntrega <= :checkout AND r.fechaRetiro >= :checkin AND r.fechaRetiro <= :checkout AND r.fechaRetiro <= :checkin AND r.fechaEntrega >= :checkout  )")
    public List <Vehiculo> listadoVehiculoDisponiblesFechas (@Param("checkin") LocalDate fRetiro, @Param("checkout") LocalDate fDevolcion);

    @Query("SELECT v.precio FROM Vehiculo v WHERE v.id = :id ")
    public Double precioDiaarioVehiculo (@Param("id") Long id);
}
