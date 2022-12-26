package PP.alquilerVehiculo.repositorios;

import PP.alquilerVehiculo.entidad.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
//    @Query ("SELECT e FROM Empleado e WHERE e.mail = :correo")
//    public Empleado buscarPorEmail (@Param("mail") String correo);
}
