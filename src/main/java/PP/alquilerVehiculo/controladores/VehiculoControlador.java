package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vehiculo")
public class VehiculoControlador {
    @Autowired
    VehiculoServicio vehiculoServicio;

    @GetMapping("/tablav")

    public String listarAutos(ModelMap modelo) throws Exception {
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        modelo.addAttribute("autos", listaAutos);
        return "autos";

    }
}
