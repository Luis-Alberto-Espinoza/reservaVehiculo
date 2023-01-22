package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.*;
import PP.alquilerVehiculo.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping(value = "/contrato")
@Controller
public class ContratoControlador {
    @Autowired
    ReservaServicio reservaServicio;
    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    VehiculoServicio vehiculoServicio;
    @Autowired
    ContratoServicio contratoServicio;
    @Autowired
    EmpleadoServicio empleadoServicio;

    @GetMapping("/")
    public String index_contrato(ModelMap modelo, @RequestParam long ide) throws Exception {
        System.out.println("llegue al controlador de contratos");
        Empleado empleado = empleadoServicio.findById(ide);
        modelo.put("empleadoLog", empleado);
        return "contrato_index";
    }

    @GetMapping("/generar_contrato")
    public String generar_contrato(ModelMap modelo, @RequestParam long idres, @RequestParam long ide) throws Exception {
        System.out.println("llegue al controlador de contratos line 44");
        Empleado empleado = empleadoServicio.findById(ide);
        //Vehiculo auto = vehiculoServicio.findById(idv);
        //Cliente cliente = clienteServicio.findById(idc);
        ReservaWeb reservaWeb = reservaServicio.findById(idres);
        System.out.println(reservaWeb.getId());
        Cliente cliente = reservaWeb.getCliente();
        Vehiculo auto = reservaWeb.getDatosVehiculo();
        modelo.put("empleadoLog", empleado);
        modelo.put("datoReserva", reservaWeb);
        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
        return "genera_contrato";
    }

    @PostMapping("/confi_contrato")
//    public String reserva(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc
    public String reserva(ModelMap modelo, @RequestParam Long id_reserva, @RequestParam Long ide
    ) throws Exception {
        ReservaWeb reserva = reservaServicio.findById(id_reserva);
        Empleado empleado = empleadoServicio.findById(ide);
        List<Date> listadoFechas = new ArrayList<>();
//        listadoFechas.add(fDevolucion);
//        listadoFechas.add(fRetiro);
        Date fechaRegistrp = new Date();
        contratoServicio.guardarContrato(reserva, empleado);
        Vehiculo auto = reserva.getDatosVehiculo();
        Cliente cliente = reserva.getCliente();
        Contrato contrato = contratoServicio.contratoXidReserva(reserva.getId());
        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
        modelo.put("empleadoLog", empleado);
        modelo.put("id_contrato", contrato.getId());
        return "exito_contrato";
    }
}
