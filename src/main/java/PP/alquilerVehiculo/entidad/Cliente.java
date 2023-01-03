package PP.alquilerVehiculo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "Cliente")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Persona{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    //@GeneratedValue(generator = "uuid")
//    // @GenericGenerator(name = "uuid", strategy = "uuid2")
//    private long id ;
//    private String nombre;
//    private String apellido;
//    private String direccion;
//    private long edad;
//    private long dni;
//    private long telefono;
//    private String mail;
//    private String clave1;


    @OneToMany(mappedBy = "cliente")
    private List<ReservaWeb> reserva;
    private Date alta;
    private Date baja;
}
