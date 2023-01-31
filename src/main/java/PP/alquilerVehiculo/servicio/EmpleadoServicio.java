package PP.alquilerVehiculo.servicio;

import PP.alquilerVehiculo.entidad.Empleado;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.repositorios.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicio implements BaseService<Empleado> {
    @Autowired
    EmpleadoRepositorio empleadoRepositorio;

    @Transactional
    public void guardarMail(String mail) throws Exception {
        Empleado empleado = new Empleado();
        empleado.setMail(mail);
        empleadoRepositorio.save(empleado);
    }

    @Override
    public List<Empleado> findAll() throws Exception {
        List<Empleado> listaEmpleados = empleadoRepositorio.findAll();
        return listaEmpleados;
    }

    @Override
    public Empleado findById(long id) throws Exception {
        try {
            Optional<Empleado> entityOptional = empleadoRepositorio.findById(id);
            return entityOptional.get();

        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
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

    public Empleado buscarXmail(String correo) throws Exception {
        try {

            Empleado empleado = empleadoRepositorio.buscarPorEmail(correo);
            return empleado;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
    public String existeEmpleado(String correo){
        return empleadoRepositorio.existeEmpleado(correo);
    }
}
