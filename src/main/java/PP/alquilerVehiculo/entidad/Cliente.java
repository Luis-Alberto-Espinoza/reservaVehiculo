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
    private Date alta;
    private Date baja;

    @OneToMany(mappedBy = "cliente")
    private List<ReservaWeb> reserva;

}
