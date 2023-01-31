package PP.alquilerVehiculo.servicio;

import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.repositorios.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        return  vehiculoRepositorio.xidv_DisponibleFehcas(fRetiro, fDevolucion);
    }
    @Override
    public Vehiculo findById(long id) throws Exception {
        Optional<Vehiculo> obj = vehiculoRepositorio.findById(id);

        return obj.get();
    }

    @Override
    public Vehiculo save(Vehiculo entity) throws Exception {
        return null;
    }

    @Override
    public Vehiculo update(long id, Vehiculo entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(long id) throws Exception {
        return false;
    }
}
