package PP.alquilerVehiculo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
//
@Table(name ="Empleado")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Empleado extends Persona{
    private String typeEmpleado;
}
