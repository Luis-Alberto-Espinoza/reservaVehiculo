package PP.alquilerVehiculo.servicio;

import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.repositorios.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class VehiculoServicio implements BaseService<Vehiculo> {
    @Autowired
    VehiculoRepositorio vehiculoRepositorio;

    @Override
    public List<Vehiculo> findAll() throws Exception {
        List<Vehiculo> listaVehiculos = vehiculoRepositorio.findAll();
        return listaVehiculos;
    }

    public List<Vehiculo> autosDisponiblesXfechas(LocalDate fRetiro, LocalDate fDevolucion) throws Exception {
        System.out.println("23 sv cantidad de vehiculos" + vehiculoRepositorio.listadoVehiculoDisponiblesFechas(fRetiro, fDevolucion).size());
        return vehiculoRepositorio.listadoVehiculoDisponiblesFechas(fRetiro, fDevolucion);
    }

    @Override
    public Vehiculo findById(long id) throws Exception {
        Optional<Vehiculo> obj = vehiculoRepositorio.findById(id);

        return obj.get();
    }

    @Override
    @Transactional
    public Vehiculo save(Vehiculo entity) throws Exception {
        try {
            entity = vehiculoRepositorio.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Vehiculo update(long id, Vehiculo entity) throws Exception {
        return null;
    }

    @Override
    public void deleteById(long id) throws Exception {
        try {
            vehiculoRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public Double costoTotal(String fRetiro, String fDevolucion, Long id) {
        //con el id del vehiculo se obtiene el precio diario
        Double precio = vehiculoRepositorio.precioDiaarioVehiculo(id);

        //con ChronoUnit.DAYS se saca la diferencia en dias entre las fechas de retiro y devolucion
        long dias = DAYS.between(LocalDate.parse(fRetiro), LocalDate.parse(fDevolucion));

        //retorna la multiplicacion; de la diferencia de dias, por el precio diario del vehiculo
        return dias * precio;
    }
}
