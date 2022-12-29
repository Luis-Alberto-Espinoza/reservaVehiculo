package PP.alquilerVehiculo.repositorios;

import PP.alquilerVehiculo.entidad.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Long> {

}
