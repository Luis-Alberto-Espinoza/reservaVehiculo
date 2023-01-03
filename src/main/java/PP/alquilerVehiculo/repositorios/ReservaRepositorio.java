package PP.alquilerVehiculo.repositorios;

import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.entidad.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepositorio extends JpaRepository<ReservaWeb, Long> {//extends JpaRepository<ReservaWeb, String> {

    @Query("SELECT m FROM ReservaWeb m WHERE m.cliente.id = :id ")
    public List<ReservaWeb> buscarReservasPorUsuario(@Param("id") String id);

    @Query("SELECT r FROM ReservaWeb r  WHERE id = :id")
    //funciona //public ReservaWeb reservaxid (@Param("id") Long id);
    public ReservaWeb reservaxid(@Param("id") Long id);

    @Query("SELECT l FROM ReservaWeb l WHERE l.cliente.id = :id")
    public List<ReservaWeb> listaVehiculoXcliente(@Param("id") Long id);

}
