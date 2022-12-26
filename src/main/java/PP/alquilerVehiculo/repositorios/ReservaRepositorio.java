package PP.alquilerVehiculo.repositorios;

import PP.alquilerVehiculo.entidad.ReservaWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepositorio extends JpaRepository<ReservaWeb, Long> {//extends JpaRepository<ReservaWeb, String> {


    @Query("SELECT m FROM ReservaWeb m WHERE m.cliente.id = :id ")
    public List<ReservaWeb> buscarReservasPorUsuario(@Param("id") String id);

}
