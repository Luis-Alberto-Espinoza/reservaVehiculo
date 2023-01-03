package PP.alquilerVehiculo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Contrato")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contrato extends Base{
private String sucursal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date fechaContrato;
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "fk_empleado", nullable = false)
    private Empleado empleado;

    @OneToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "fk_reserva", nullable = false)
    private ReservaWeb reserva;
}
