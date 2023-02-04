package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Empleado;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.EmpleadoServicio;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/vehiculo")//
public class VehiculoControlador {
    @Autowired
    VehiculoServicio vehiculoServicio;
    @Autowired
    ClienteServicio clienteServicio;

    @Autowired
    EmpleadoServicio empleadoServicio;

    @GetMapping("/tablav")

    public String listarAutos(ModelMap modelo) throws Exception {
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        modelo.addAttribute("autos", listaAutos);
        return "autos";

    }
    @GetMapping("/resEmp")
    public String listarAutosReserva(ModelMap modelo, @RequestParam long dni, @RequestParam Long ide,
                                     @RequestParam("fRetiro") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fRetiro,
                                     @RequestParam(value = "fDevolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fDevolucion
                                     ) throws Exception {
        System.out.println("35 de VehiculoControlador " + fRetiro+"  -  "+fDevolucion );
        Empleado empleado = empleadoServicio.findById(ide);
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        Cliente cliente = clienteServicio.buscarXdni(dni);
        System.out.println("38 line cliente " + cliente);
        modelo.put("fRetiro", fRetiro);
        modelo.put("fDevolucion", fDevolucion);
        modelo.put("empleadoLog", empleado);
        modelo.addAttribute("autos", listaAutos);
        modelo.put("clienteLog", cliente);
        return "autos_reserva";

    }
}
