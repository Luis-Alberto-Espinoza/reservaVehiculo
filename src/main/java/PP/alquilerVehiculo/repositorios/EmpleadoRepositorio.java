package PP.alquilerVehiculo.repositorios;

import PP.alquilerVehiculo.entidad.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
    @Query("SELECT e FROM Empleado e WHERE e.mail = :mail")
    public Empleado buscarPorEmail(@Param("mail") String mail);

    @Query("SELECT COUNT(e.mail) FROM Empleado e WHERE e.mail = :mail")
    public String existeEmpleado(@Param("mail") String mail);

    @Query("SELECT COUNT(e.dni) FROM Empleado e WHERE e.dni = :dni")
    public String existeEmpleadoXdni(@Param("dni") long dni);

    @Query("Select e from Empleado e where e.dni = :dni")
    public Empleado buscarXdni(@Param("dni") long dni);
}
