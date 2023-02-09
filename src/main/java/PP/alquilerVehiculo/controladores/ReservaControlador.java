
package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Empleado;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.EmpleadoServicio;
import PP.alquilerVehiculo.servicio.ReservaServicio;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
import net.bytebuddy.asm.Advice;
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

    @Autowired
    EmpleadoServicio empleadoServicio;

    @GetMapping("/")
    public String soloReserva(ModelMap modelo, @RequestParam Long ide) throws Exception {
        System.out.println("llegue a soloreserva 42");
        Empleado empleado = empleadoServicio.findById(ide);
        System.out.println(empleado.getNombre());
        modelo.put("empleadoLog", empleado);
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        modelo.put("autos", listaAutos);
        return "reserva_de_empleado";
    }

    @GetMapping("/generar_reserva")
    public String generar_reserva(ModelMap modelo,
                                  @RequestParam Long idv,
                                  @RequestParam Long idc,
                                  String fecha1,
                                  String fecha2) throws Exception {
        Vehiculo auto = vehiculoServicio.findById(idv);
        Cliente cliente = clienteServicio.findById(idc);
        Double precioTotal = vehiculoServicio.costoTotal(fecha1,fecha2, idv);
        modelo.put("total", precioTotal);
        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
        modelo.put("fecha1", fecha1);
        modelo.put("fecha2", fecha2);
        return "reserva";
    }
    @GetMapping("/generar_reserva_empleado")
    public String generar_reserva_empleado(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc,
                                           @RequestParam String fRetiro,
                                           @RequestParam String fDevolucion,
                                           @RequestParam Long ide) throws Exception {
        Vehiculo auto = vehiculoServicio.findById(idv);
        Cliente cliente = clienteServicio.findById(idc);
        Empleado empleado= empleadoServicio.findById(ide);
        Double precioTotal = vehiculoServicio.costoTotal(fRetiro,fDevolucion, idv);
        modelo.put("total", precioTotal);
        modelo.put("fRetiro", fRetiro);
        modelo.put("fDevolucion", fDevolucion);
        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
        modelo.put("empleadoLog", empleado);
        return "reserva_empleado";
    }
    @PostMapping("/confi_reserva")
    public String reserva(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc,
                          @RequestParam("fecha1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fRetiro,
                          @RequestParam(value = "fecha2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fDevolucion
    ) throws Exception {

        Cliente cliente = clienteServicio.findById(idc);
        Vehiculo auto = vehiculoServicio.findById(idv);

        //se genera la fecha actual para dejar asentado la fecha de gestion de la reserva
        Date fechaActual = new Date();
        LocalDate fechaRegistro = fechaActual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //se crea una lista para pasar mas rapido todas las fechas
        System.out.println("90 fechas 1 "+ fRetiro+"  2: "+fDevolucion);
        List<LocalDate> listadoFechas = new ArrayList<>();
        listadoFechas.add(fDevolucion);
        listadoFechas.add(fRetiro);
        listadoFechas.add(fechaRegistro);
          try {
            reservaServicio.guardarReserva(cliente, auto, listadoFechas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //con el id del cliente se busca la reserva recien guardada para poder pasarla al modelo
        ReservaWeb reservaWeb = reservaServicio.ultimaReserva(cliente);
        modelo.put("clienteLog", cliente);
        modelo.put("id_ures", reservaWeb.getId());
        return "/exito_reserva";
    }
    @PostMapping("/confi_reserva_empleado")
    public String reserva_empleado(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc,@RequestParam Long ide,
                          @RequestParam("fRetiro") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fRetiro,
                          @RequestParam(value = "fDevolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fDevolucion
    ) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
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
           try {
            reservaServicio.guardarReserva(cliente, auto, listadoFechas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //con el id del cliente se busca la reserva recien guardada para poder pasarla al modelo
        ReservaWeb reservaWeb = reservaServicio.ultimaReserva(cliente);
        modelo.put("empleadoLog", empleado);
        modelo.put("clienteLog", cliente);
        modelo.put("id_ures", reservaWeb.getId());
        return "/exito_reserva_empleado";
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
        return "reserva";
    }

    @GetMapping("/delet_reserva")
    public String eliminarReserva(long id_reserva, ModelMap modelo) throws Exception {
        ReservaWeb reservaWeb = reservaServicio.findById(id_reserva);
        System.out.println("129 ResContr reserva id " + reservaWeb.getId());
        Cliente cliente = clienteServicio.buscarXdni(reservaWeb.getCliente().getDni());
        reservaServicio.deleteById(id_reserva);
        List<ReservaWeb> autosReservados = reservaServicio.lDeAutosR(cliente);
        modelo.put("autoReservado", autosReservados);
        modelo.put("clienteLog", cliente);
        modelo.addAttribute("id", cliente.getId());
        return "/hitorial_reserva_cliente";
    }

    @GetMapping("/recibir_fecha")
    public String recibir_fecha(@RequestParam("fRetiro") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fRetiro,
                             @RequestParam(value = "fDevolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fDevolucion,
                             long idc, ModelMap modelo) throws Exception {
        Cliente cliente = clienteServicio.findById(idc);
        List<Vehiculo> vehiculosDisponibles = vehiculoServicio.autosDisponiblesXfechas(fRetiro, fDevolucion);
        modelo.put("autos", vehiculosDisponibles);
        modelo.put("clienteLog", cliente);
        modelo.put("fecha1", fRetiro);
        modelo.put("fecha2", fDevolucion);
        return  "autos_disponibles";

    }

}
