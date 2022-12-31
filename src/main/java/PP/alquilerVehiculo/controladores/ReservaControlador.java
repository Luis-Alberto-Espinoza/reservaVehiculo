
package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.ReservaServicio;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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
    public String index(ModelMap modelo, @RequestParam Long id) throws Exception {
        ReservaWeb reserva = reservaServicio.findById(id);
        modelo.addAttribute("valija", reserva);
        return "reserva";


    }

    @GetMapping("/generar_reserva")
    public String generar_reserva(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc) throws Exception {
        Vehiculo auto = vehiculoServicio.findById(idv);
        System.out.println("42 auto.modelo "+ auto.getModelo());
        Cliente cliente = clienteServicio.findById(idc);
        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
        return "reserva";
    }

    @PostMapping("/confi_reserva")
    public String reserva(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc) throws Exception {

        System.out.println(idv + " idv");
        System.out.println(idc + " idc");
        Vehiculo auto = vehiculoServicio.findById(idv);
        Cliente cliente = clienteServicio.findById(idc);
        System.out.println("Cliente " + cliente.getId() + " = " + cliente.getNombre());
        System.out.println("auto " + auto.getId() + " = " + auto.getModelo());

        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
        return "index_cliente";

    }

}
