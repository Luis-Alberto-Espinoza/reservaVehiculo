
package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.ReservaServicio;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/reserva")
public class ReservaControlador {
    @Autowired
    ReservaServicio reservaServicio;
    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    VehiculoServicio vehiculoServicio;

    @GetMapping("/")
    public String soloReserva(ModelMap modelo) throws Exception {
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        modelo.put("autos", listaAutos);
        return "reserva_de_empleado";
    }

    @GetMapping("/generar_reserva")
    public String generar_reserva(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc) throws Exception {
        Vehiculo auto = vehiculoServicio.findById(idv);
        Cliente cliente = clienteServicio.findById(idc);
        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
        return "reserva";
    }

    @PostMapping("/confi_reserva")
    public String reserva(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc,
                          @RequestParam("fRetiro") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fRetiro,
                          @RequestParam(value = "fDevolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fDevolucion
    ) throws Exception {

        Cliente cliente = clienteServicio.findById(idc);
        Vehiculo auto = vehiculoServicio.findById(idv);

        //se genera la fecha actual para dejar asentado la fecha de gestion de la reserva
        Date fechaActual = new Date();
        LocalDate fechaRegistro = fechaActual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //se crea una lista para pasar mas rapido todas las fechas
        List<LocalDate> listadoFechas = new ArrayList<>();
        listadoFechas.add(fDevolucion);
        listadoFechas.add(fRetiro);
        listadoFechas.add(fechaRegistro);
        System.out.println("el listado de fechas tiene una cantidad de : " + listadoFechas.size());
        System.out.println("listado de fechas 1 - " + listadoFechas.get(0));
        System.out.println("listado de fechas 2 - " + listadoFechas.get(1));
        System.out.println("listado de fechas 3 - " + listadoFechas.get(2));

        try {
            reservaServicio.guardarReserva(cliente, auto, listadoFechas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //con el id del cliente se busca la reserva recien guardada para poder pasarla al modelo
        ReservaWeb reservaWeb = reservaServicio.ultimaReserva(cliente);

        modelo.put("id_ures", reservaWeb.getId());
        return "/exito_reserva";
    }

    @GetMapping("/mis_reservas")
    public String historial(ModelMap modelo, @RequestParam Long idc) throws Exception {
        Cliente cliente = clienteServicio.findById(idc);
        modelo.put("clienteLog", cliente);
        return "/cliente/";
    }

    @GetMapping("/hreservas")
    public String h_reserva(@RequestParam Long id, ModelMap modelo) {
        Cliente cliente = clienteServicio.buscarPorId(id);
        List<ReservaWeb> autosReservados = reservaServicio.lDeAutosR(cliente);
        modelo.put("autoReservado", autosReservados);
        modelo.put("clienteLog", cliente);

        return "/hitorial_reserva_cliente";
    }

    @GetMapping("/edit_reserva")
    public String editarReserva(long id_reserva, ModelMap modelo) throws Exception {
        ReservaWeb reservaWeb = reservaServicio.findById(id_reserva);
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        modelo.addAttribute("autos", listaAutos);
        Cliente cliente = clienteServicio.buscarXdni(reservaWeb.getCliente().getDni());
        modelo.put("clienteLog", cliente);
        return "autos_reserva";
    }

    @GetMapping("/delet_reserva")
    public String eliminarReserva(long id_reserva, ModelMap modelo) throws Exception {
        ReservaWeb reservaWeb = reservaServicio.findById(id_reserva);
        System.out.println("129 ResContr reserva id " + reservaWeb.getId());
        Cliente cliente = clienteServicio.buscarXdni(reservaWeb.getCliente().getDni());
        reservaServicio.delete(id_reserva);
        List<ReservaWeb> autosReservados = reservaServicio.lDeAutosR(cliente);
        modelo.put("autoReservado", autosReservados);
        modelo.put("clienteLog", cliente);
        modelo.addAttribute("id", cliente.getId());
        return "/hitorial_reserva_cliente";
    }
}
