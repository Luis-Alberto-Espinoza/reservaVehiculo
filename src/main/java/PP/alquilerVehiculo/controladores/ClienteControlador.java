package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.excepciones.ClienteServiceException;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.EmpleadoServicio;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private VehiculoServicio vehiculoServicio;
    @Autowired
    private EmpleadoServicio empleadoServicio;


    @GetMapping("/")
    //http://localhost:9000/cliente/?correo=Correocliente_14%40correo.com&password=123456
    public String clienteInicio(@RequestParam String correo, ModelMap modelo) throws Exception {
//    public String clienteInicio(@RequestParam String correo, @RequestParam String password, ModelMap modelo) throws Exception {

        Cliente usuario = clienteServicio.buscarXcorreo(correo);
        System.out.println(">==== usuario " + usuario.getApellido());
        modelo.addAttribute("clienteLog" , usuario);
        List<Vehiculo> listVehiculos = vehiculoServicio.findAll();
        modelo.addAttribute("autos", listVehiculos);
        return "index_cliente";
    }

    @GetMapping("/editar-perfil")
    public String editarPerfil(@RequestParam Long id, ModelMap modelo) {
        Cliente clienteLog = null;
        clienteLog = clienteServicio.buscarPorId(id);

        modelo.addAttribute("perfil", clienteLog);
        return "perfil";
    }

    @PostMapping("/actualizar-perfil")
    public String actualizar(ModelMap modelo, @RequestParam Long id,
                             @RequestParam String nombre, @RequestParam String apellido,
                             @RequestParam String email, //
                             //@RequestParam long edad, @RequestParam long dni, @RequestParam long telefono,
                             @RequestParam String clave1, @RequestParam String clave2) throws ClienteServiceException {
        Cliente clienteLog = null;
        clienteLog = clienteServicio.buscarPorId(id);
        clienteServicio.modificar(id, nombre, apellido, email, clave1, clave2);// , direccion, edad, telefono, dni);
        clienteLog = clienteServicio.buscarPorId(id);
        System.out.println("cliContr 70 :" + clienteLog.getNombre() + " id:" + clienteLog.getId());
        return "redirect:/login";
    }

    @GetMapping("/clientes")
    public String mostrarClientes(ModelMap modelo) throws Exception {
        List<Cliente> listaCliente = clienteServicio.findAll();
        modelo.addAttribute("listClient", listaCliente);
        return "clientes";
    }
}
