package PP.alquilerVehiculo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
@Controller
@RequestMapping("/empleado")
public class EmpleadoControlador {
    @GetMapping("/mostrar_e")
    public String mostrar_e(){
        return  "indexe";
    }

}
