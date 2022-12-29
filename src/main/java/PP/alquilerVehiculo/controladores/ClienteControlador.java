package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.excepciones.ClienteServiceException;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {
    @Autowired
    private ClienteServicio clienteServicio;
    @GetMapping("/editar-perfil")
    public String editarPerfil(HttpSession session, @RequestParam long id, ModelMap model) {

        Cliente cliente;
        // Verificación de que el Cliente logueado coincida con el id del Cliente a editar su perfil
        //Cliente login = (Cliente) session.getAttribute("usuariosession");
       // if (login == null || login.getId() != (id)) {
            return "redirect:/inicio";
        //}

        // Devolvemos el HTML de "Configurar Perfil"
    }


//    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @PostMapping("/actualizar-perfil")
    public String actualizar(ModelMap model, HttpSession session, @RequestParam String id,
                             @RequestParam String nombre, @RequestParam String apellido,
                             @RequestParam String email, @RequestParam String direccion,
                             @RequestParam long edad, @RequestParam long dni, @RequestParam long telefono,
                             @RequestParam String clave1, @RequestParam String clave2) throws ClienteServiceException {

        // Verificación de que el Cliente logueado coincida con el id del Cliente a editar su perfil
        Cliente login = (Cliente) session.getAttribute("usuariosession");
//        if (login == null || !Objects.equals(login.getId(), id.toString())) {
//            return "redirect:/inicio";
//        }

        // Buscamos al Cliente por su id, lo modificamos y redireccionamos al inicio
        clienteServicio.modificar(Long.parseLong(id), nombre, apellido, email, clave1, clave2, direccion, edad, telefono,dni);

        // Si la modificación fue exitosa, pisamos los atributos antiguos de la sesión con los nuevos
        session.setAttribute("usuariosession", clienteServicio.buscarPorId(Long.parseLong(id)));

        return "redirect:/inicio";
    }
    @GetMapping("/clientes")
    public String mostrarClientes(ModelMap modelo) throws Exception {
        List<Cliente> listaCliente = clienteServicio.findAll();
        modelo.addAttribute("listClient",listaCliente);
    return "clientes";
    }

}
