package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Empleado;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoControlador {
    @Autowired
    private EmpleadoServicio empleadoServicio;


    @GetMapping("/ventas")
    //http://localhost:9000/cliente/?correo=Correocliente_14%40correo.com&password=123456
    public String ventasHome(@RequestParam String correo, ModelMap modelo) throws Exception {
        Empleado usuario = empleadoServicio.buscarXmail(correo);
        System.out.println(">==== usuario " + usuario.getApellido());
        modelo.addAttribute("empleadoLog" , usuario);
        return "index_ventas";
    }
    @GetMapping("/tabla")
    public String mostrar_e(ModelMap modelo) throws Exception {
        List<Empleado> listaEmpleados = empleadoServicio.findAll();
        modelo.addAttribute("empleados", listaEmpleados);

        return "hitorial_reserva_cliente";
    }

    @PostMapping("/mostrar")
    public String mostrar(@RequestParam String email) {
        try {
            empleadoServicio.guardarMail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "indexe";
    }
    @GetMapping("/prueba_empleado")
    public void LEVentas()throws Exception {
        System.out.println("llegaste a prba empleado!!!! ");
       // Empleado empleado = empleadoServicio.findById(11120L);
       // System.out.println (empleado.getTypeEmpleado());
        Empleado empleado = empleadoServicio.buscarXmail("CorreoEmpleado_09@correo.com");
        System.out.println(empleado.getId()+" id "+ empleado.getTypeEmpleado()+"  "+empleado.getNombre());
    }

}
