package PP.alquilerVehiculo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "vehiculo")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehiculo extends Base{
//
    private String patente;
    private String marca;
    private String modelo;
    private String color;
    private String tipoVehiculo;
    private String cilindradaMotor;
    private String combustible;
    private String typeGama;
    private Boolean operativo;


}
