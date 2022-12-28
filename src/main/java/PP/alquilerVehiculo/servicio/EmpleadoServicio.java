package PP.alquilerVehiculo.servicio;

import PP.alquilerVehiculo.entidad.Empleado;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpleadoServicio implements BaseService<Empleado> {

    @Override
    public List<Empleado> findAll() throws Exception {
        return null;
    }

    @Override
    public Empleado findById(long id) throws Exception {
        return null;
    }

    @Override
    public Empleado save(Empleado entity) throws Exception {
        return null;
    }

    @Override
    public Empleado update(long id, Empleado entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(long id) throws Exception {
        return false;
    }
}
