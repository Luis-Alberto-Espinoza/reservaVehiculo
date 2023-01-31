package PP.alquilerVehiculo.repositorios;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepositorio extends JpaRepository<ReservaWeb, Long> {//extends JpaRepository<ReservaWeb, String> {

    @Query("SELECT m FROM ReservaWeb m WHERE m.cliente.id = :id ")
    public List<ReservaWeb> buscarReservasPorUsuario(@Param("id") String id);

    @Query("SELECT r FROM ReservaWeb r  WHERE id = :id")
    //funciona //public ReservaWeb reservaxid (@Param("id") Long id);
    public ReservaWeb reservaxid(@Param("id") Long id);

    @Query("SELECT r FROM ReservaWeb r  WHERE fechaReserva = :fechaReserva")
    public ReservaWeb reservaxfechaRegistro(@Param("fechaReserva") Date fechaReserva);

    @Query("SELECT l FROM ReservaWeb l WHERE l.cliente.id = :id")
    public List<ReservaWeb> listaVehiculoXcliente(@Param("id") Long id);

    @Query("SELECT MAX(r) FROM ReservaWeb r WHERE r.cliente.id = :id")
    public ReservaWeb ultimaReserva(@Param("id") Long id);// funciona

    @Query("SELECT r FROM ReservaWeb r WHERE r.fechaEntrega < :fechaRetiro AND r.fechaRetiro > :fechaEntrega")
    public List<ReservaWeb> vehiculoDisponiblesLista(@Param("fechaRetiro") LocalDate fRetiro, @Param("fechaEntrega") LocalDate fDevolcion);

    /////metodo 1
    @Query("SELECT r FROM ReservaWeb r WHERE r.fechaEntrega >= :checkin AND r.fechaEntrega <= :checkout AND r.datosVehiculo.id = :id")
    public List<ReservaWeb> metodo1(@Param("checkin") LocalDate fRetiro, @Param("checkout") LocalDate fDevolcion, @Param("id") Long id);

    /////2
    @Query("SELECT r FROM ReservaWeb r WHERE r.fechaRetiro >= :checkin AND r.fechaRetiro <= :checkout  AND r.datosVehiculo.id = :id")
    public List<ReservaWeb> metodo2(@Param("checkin") LocalDate fRetiro, @Param("checkout") LocalDate fDevolcion, @Param("id") Long id);

    /////3

    @Query("SELECT r FROM ReservaWeb r WHERE r.fechaRetiro <= :checkin AND r.fechaEntrega >= :checkout  AND r.datosVehiculo.id = :id")
    public List<ReservaWeb> metodo3(@Param("checkin") LocalDate fRetiro, @Param("checkout") LocalDate fDevolcion, @Param("id") Long id);

    @Query("SELECT r FROM ReservaWeb r WHERE NOT (r.fechaEntrega >= :checkin AND r.fechaEntrega <= :checkout AND r.fechaRetiro >= :checkin AND r.fechaRetiro <= :checkout AND r.fechaRetiro <= :checkin AND r.fechaEntrega >= :checkout  AND  r.datosVehiculo.id = :id)")
    public List <ReservaWeb> metodoCombinado (@Param("checkin") LocalDate fRetiro, @Param("checkout") LocalDate fDevolcion, @Param("id") Long id);

    //// x id vehiculo
    @Query("SELECT r FROM ReservaWeb r WHERE r.datosVehiculo.id = :id")
    public List<ReservaWeb> xIdVehiculo(@Param("id") Long id);

    //// x fechas encontrar los autos disponibles

    /////a
//    @Query("SELECT r FROM ReservaWeb r RIGHT JOIN Vehiculo v on r.datosVehiculo = v.id WHERE r.id is null")
//    public List<ReservaWeb> metodoA();
//    public List<ReservaWeb> metodoA(@Param("checkin") LocalDate fRetiro, @Param("checkout") LocalDate fDevolcion);

//    SELECT * FROM reserva r
//    INNER JOIN vehiculo v on r.datos_vehiculo_id = v.id
//    WHERE v.color = 'blanco'



    //esta consulta trae los vehiculos que no tienen reservas !!!!
//    SELECT * FROM reserva r
//    RIGHT  JOIN vehiculo v on r.datos_vehiculo_id = v.id
//    WHERE r.id is null

}
