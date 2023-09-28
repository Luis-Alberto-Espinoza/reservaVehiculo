package PP.alquilerVehiculo.repositorios;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Contrato;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepositorio extends JpaRepository<Contrato, Long> {
    @Query("SELECT c FROM Contrato c WHERE c.reserva.id = :id")
    public Contrato contratoXidReserva(@Param("id") Long id);

    @Query("SELECT c FROM Contrato c WHERE c.reserva.cliente.id = :id")
    public List<Cliente> contratoXidCliente(@Param("id") Long id);

    @Query("SELECT count (reserva.id) FROM Contrato WHERE reserva.id = :idres")
    public String encontrarReservaLigada(@Param("idres") Long idres);
}
