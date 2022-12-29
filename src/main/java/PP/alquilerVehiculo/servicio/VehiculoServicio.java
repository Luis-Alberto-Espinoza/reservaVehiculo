package PP.alquilerVehiculo.servicio;

import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.repositorios.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServicio implements BaseService<Vehiculo> {
    @Autowired
    VehiculoRepositorio vehiculoRepositorio;
    @Override
    public List<Vehiculo> findAll() throws Exception {
        List<Vehiculo> listaVehiculos = vehiculoRepositorio.findAll();
        return listaVehiculos;
    }

    @Override
    public Vehiculo findById(long id) throws Exception {
        return null;
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
