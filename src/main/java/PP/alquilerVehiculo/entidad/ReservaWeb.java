package PP.alquilerVehiculo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Reserva")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ReservaWeb extends Base {

    private Date fechaReserva;
    @ManyToOne (fetch = FetchType.EAGER )
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente cliente;



    @OneToOne
    @JoinColumn(name = "datos_vehiculo_id")
    private Vehiculo datosVehiculo;
    private Date fechaRetiro;
    private Date fechaEntrega;


}
