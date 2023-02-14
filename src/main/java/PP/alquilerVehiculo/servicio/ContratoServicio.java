package PP.alquilerVehiculo.servicio;

import PP.alquilerVehiculo.entidad.*;
import PP.alquilerVehiculo.repositorios.ContratoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContratoServicio implements BaseService<Contrato> {
    @Autowired
    ContratoRepositorio contratoRepositorio;

    @Autowired
    ReservaServicio reservaServicio;

    public void registrarContrato(Empleado empleado, ReservaWeb reserva) throws Exception {
        Contrato contrato = new Contrato();
        contrato.setEmpleado(empleado);
        contrato.setReserva(reserva);
        contrato.setFechaContrato(new Date());
        contratoRepositorio.save(contrato);
    }

    public Contrato contratoXidReserva(long id) throws Exception {

        Contrato optional = contratoRepositorio.contratoXidReserva(id);
        return optional;
    }

    public List<Contrato> ListCxCliente() throws Exception {
        return null;
    }

    public Optional<Contrato> buscarXreserva(long id) throws Exception {
        return contratoRepositorio.findById(id);
    }


    @Override
    public List<Contrato> findAll() throws Exception {
        return null;
    }

    @Override
    public Contrato findById(long id) throws Exception {
        return null;
    }


    @Override
    public Contrato save(Contrato entity) throws Exception {
        return null;
    }

    @Override
    public Contrato update(long id, Contrato entity) throws Exception {
        return null;
    }

    @Override
    public void deleteById(long id) throws Exception {

    }


    public void guardarContrato(ReservaWeb reserva, Empleado empleado) {
        Contrato contrato = new Contrato();
        contrato.setReserva(reserva);
        contrato.setFechaContrato(new Date());
        contrato.setEmpleado(empleado);
        contratoRepositorio.save(contrato);
    }

    public boolean validarReserva(long idres) throws Exception {
        String reservaWeb = contratoRepositorio.encontrarReservaLigada(idres);
        LocalDate fechaActual = LocalDate.now();
        if(reservaWeb.equals("1")){
            return false;
        }else if(reservaServicio.findById(idres).getFechaRetiro().compareTo(fechaActual) > 0 ) {
            return true;
        }else {
            return false;
        }
    }
}
