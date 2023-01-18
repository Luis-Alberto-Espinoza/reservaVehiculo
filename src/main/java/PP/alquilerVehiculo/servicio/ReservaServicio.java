package PP.alquilerVehiculo.servicio;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.repositorios.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServicio implements BaseService<ReservaWeb> {
    @Autowired
    public ReservaRepositorio reservaRepositorio;

//    public ReservaServicio(ReservaRepositorio reservaRepositorio) {
//        this.reservaRepositorio = reservaRepositorio;
//    }

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
    public boolean delete(long id) throws Exception {
        try {
            if (reservaRepositorio.existsById(id)) {
                reservaRepositorio.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }

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
    public void guardarReserva(Cliente cliente, Vehiculo vehiculo) {
        ReservaWeb newReserva = new ReservaWeb();
        newReserva.setFechaReserva(new Date());
        newReserva.setCliente(cliente);
        newReserva.setDatosVehiculo(vehiculo);
        reservaRepositorio.save(newReserva);
    }

        public List<ReservaWeb> lDeAutosR(Cliente cliente) {
//        List<ReservaWeb> listaVehiculosR = new ArrayList<>();
        List<ReservaWeb> listaVehiculosR = reservaRepositorio.listaVehiculoXcliente(cliente.getId());


        return listaVehiculosR;
    }

    public void prueba(ReservaWeb reservaWeb) {
        System.out.println(reservaWeb.getId() + " linea 111 RS");
        ReservaWeb clienteReserva = reservaRepositorio.reservaxid(reservaWeb.getId());
        System.out.println(" 113 RS " + clienteReserva.getDatosVehiculo().getMarca());
    }

    public void pruebaD(Cliente cliente) {
        System.out.println(" 120 rs " + cliente.getId() + "  " + cliente.getNombre());
        List<ReservaWeb> listaDeReservaXcliente = reservaRepositorio.listaVehiculoXcliente(cliente.getId());

        System.out.println(listaDeReservaXcliente.size() + " linea 122 RS");
        System.out.println(listaDeReservaXcliente.get(1).getDatosVehiculo().getMarca() + " linea 123 RS");

    }
}
