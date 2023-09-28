package PP.alquilerVehiculo.servicio;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.repositorios.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServicio implements BaseService<ReservaWeb> {
    @Autowired
    public ReservaRepositorio reservaRepositorio;

    @Override
    @Transactional
    public List<ReservaWeb> findAll() throws Exception {
        try {
            List<ReservaWeb> entities = reservaRepositorio.findAll();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ReservaWeb findById(long id) throws Exception {
        try {
            Optional<ReservaWeb> entityOptional = reservaRepositorio.findById(id);
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ReservaWeb save(ReservaWeb entity) throws Exception {
        try {
            entity = reservaRepositorio.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteById(long id) throws Exception {
        try {
            reservaRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ReservaWeb update(long id, ReservaWeb entity) throws Exception {
        try {
            Optional<ReservaWeb> entityoptional = reservaRepositorio.findById(id);
            ReservaWeb reservaWeb = entityoptional.get();
            reservaWeb = reservaRepositorio.save(reservaWeb);
            return reservaWeb;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void guardarReserva(Cliente cliente, Vehiculo vehiculo, List<LocalDate> lFechas) throws Exception {
        //Se crea la nueva reserva y se le setea los atributos recibidos
        ReservaWeb newReserva = new ReservaWeb();
        newReserva.setCliente(cliente);
        newReserva.setDatosVehiculo(vehiculo);
        newReserva.setFechaRetiro(lFechas.get(1));
        newReserva.setFechaEntrega(lFechas.get(0));
        newReserva.setFechaReserva(lFechas.get(2));
        //Se hace persistir a la nueva reserva
        reservaRepositorio.save(newReserva);
    }

    public List<ReservaWeb> lDeAutosR(Cliente cliente) {
        List<ReservaWeb> listaVehiculosR = reservaRepositorio.listaVehiculoXcliente(cliente.getId());
        return listaVehiculosR;
    }

    public void prueba(ReservaWeb reservaWeb) {
        ReservaWeb clienteReserva = reservaRepositorio.reservaxid(reservaWeb.getId());
    }

    public void pruebaD(Cliente cliente) {
        List<ReservaWeb> listaDeReservaXcliente = reservaRepositorio.listaVehiculoXcliente(cliente.getId());
    }

    public ReservaWeb buscarXfechaRegistro(Date fechaReserva) {
        return reservaRepositorio.reservaxfechaRegistro(fechaReserva);
    }

    public ReservaWeb ultimaReserva(Cliente cliente) {
        return reservaRepositorio.ultimaReserva(cliente.getId());
    }

    public boolean disponibilidadReserva(LocalDate fRetiro, LocalDate fDevolucion, Vehiculo vehiculo) throws Exception {

        boolean sePuedeReservar = false;
        try {
            List<ReservaWeb> metodo1 = reservaRepositorio.metodo1(fRetiro, fDevolucion, vehiculo.getId());
            List<ReservaWeb> metodo2 = reservaRepositorio.metodo2(fRetiro, fDevolucion, vehiculo.getId());
            List<ReservaWeb> metodo3 = reservaRepositorio.metodo3(fRetiro, fDevolucion, vehiculo.getId());
            List<ReservaWeb> metodoC = reservaRepositorio.metodoCombinado(fRetiro, fDevolucion, vehiculo.getId());
            if (metodo1.size() == 0 & metodo2.size() == 0 & metodo3.size() == 0) {
                sePuedeReservar = true;
            }
            return sePuedeReservar;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
