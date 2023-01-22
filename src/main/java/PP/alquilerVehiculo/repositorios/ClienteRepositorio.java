package PP.alquilerVehiculo.repositorios;

import PP.alquilerVehiculo.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
//similar a un DAO patron que separa la base de datos de la logica de la api
//    @Query("SELECT u FROM Cliente u WHERE u.mail = :mail")
//    public Cliente buscarPorMail(@Param("mail") String mail);
//
//    @Query(value = "SELECT * FROM Cliente", nativeQuery = true)
//    List<Cliente> findAllActivo();

    @Query("SELECT c FROM Cliente c WHERE c.mail = :mail")
    public Cliente buscarPorMail(@Param("mail") String mail);

    @Query("SELECT c FROM Cliente c WHERE c.dni = :dni")
    public Cliente buscarPorDNI(@Param("dni") Long dni);

}
