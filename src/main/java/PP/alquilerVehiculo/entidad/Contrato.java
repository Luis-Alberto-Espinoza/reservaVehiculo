package PP.alquilerVehiculo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "Contrato")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contrato extends Base{
private String sucursal;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "fk_empleado", nullable = false)
    private Empleado empleado;

    @OneToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "fk_reserva", nullable = false)
    private ReservaWeb reserva;
}
