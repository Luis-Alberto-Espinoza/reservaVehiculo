package PP.alquilerVehiculo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;


@Table(name ="Empleado")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Empleado extends Persona{
    private LocalDate alta;
    private LocalDate baja;
    private String typeEmpleado;
    @OneToMany(mappedBy = "empleado")
    private List<Contrato> contrato;
}
