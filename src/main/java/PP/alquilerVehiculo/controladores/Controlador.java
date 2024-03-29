package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Empleado;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.excepciones.ClienteServiceException;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.EmpleadoServicio;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/")
@Controller
public class Controlador {
    //ATRIBUTO - Cliente Service
    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    VehiculoServicio vehiculoServicio;
    @Autowired
    private EmpleadoServicio empleadoServicio;


    //Método que devolverá el index.html cuando se ingrese a la url raíz de la aplicación
    @GetMapping("/")
    public String index(ModelMap modelo) throws Exception {
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        modelo.addAttribute("autos", listaAutos);
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Nombre de Usuario o clave incorrecto");
        }
        if (logout != null) {
            model.put("logout", "Ha salido correctamente de la plataforma.");
        }
        return "login.html";
    }

    //Método que devolverá el registro.html cuando se ingrese a la url raíz/registro
    @GetMapping("/registro")
    public String registro(ModelMap modelo) {
        //Devolvemos el html
        return "registro.html";
    }

    //Método que responderá a una petición POST solicitada en la url raíz/registrar y recibirá una serie de argumentos
    @PostMapping("/registrar")
    public String registrar(ModelMap modelo,
                            @RequestParam String nombre, @RequestParam String apellido,
                            @RequestParam String email, @RequestParam String clave1, @RequestParam String clave2,
                            @RequestParam String direccion, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fNacimiento,
                            @RequestParam Long telefono, @RequestParam Long dni) throws ClienteServiceException {

        //Llamamos al método registrar de ClienteServicio y le pasamos los parámetros recibidos por el controlador
        if (clienteServicio.registrar(nombre, apellido, email, clave1, clave2, direccion, fNacimiento, telefono, dni)) {
            //Inyectamos textos a los campos de exito.html
            modelo.put("titulo", "¡Bienvenido, encuentre su Auto!");
            modelo.put("descripcion", "Tu usuario ha sido registrado con éxito.");

            //Página que va a retornar si todo sale todo bien
            return "exito.html";
        } else {
            //Añadimos el Objeto ModelMap en los parámetros y usamos su método .put() para insertar un valor por pantalla.
            //Seteamos los mismos valores recibidos como argumentos dentro de los inputs del HTML
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("email", email);
            modelo.put("clave1", clave1);
            modelo.put("direccion", direccion);
            modelo.put("fNacimiento", fNacimiento);
            modelo.put("telefono", telefono);
            modelo.put("dni", dni);
            //Página que va a retornar si algo sale mal
            return "registro.html";
        }
    }

    /*metodo simil LOGIN */
    @PostMapping("/usuarioType")
    public String usuarioType(ModelMap modelo, String correo, String password) throws Exception {
        String respuesta = validadorUsuario(correo, password);
        if (respuesta.equals("cliente")) {
            Cliente cliente = clienteServicio.buscarXcorreo(correo);
            modelo.put("clienteLog", cliente);
            modelo.addAttribute("correo", cliente.getMail());
            return "index_cliente";
        }
        if (respuesta.equals("empleado")) {
            Empleado empleado = empleadoServicio.buscarXmail(correo);
            if (empleado.getTypeEmpleado().toLowerCase().equals("ventas")) {
                modelo.put("empleadoLog", empleado);
                return "index_ventas";
            } else if (empleado.getTypeEmpleado().toLowerCase().equals("gerente")) {
                modelo.put("empleadoLog", empleado);
                return "index_gerente";
            } else if (empleado.getTypeEmpleado().toLowerCase().equals("administrador")) {
                modelo.put("empleadoLog", empleado);
                return "index_admin";
            }
        }
        return "login";
    }

    /* con el correo se determina a que base de datos pertenece '"empleado" o "cliente"'
     * con el password se lo compara con la clave del registro en la bd*/
    public String validadorUsuario(String correo, String password) throws Exception {
        String respuesta = "";
        if (clienteServicio.hayCliente(correo).equals("1")) {
            if (clienteServicio.buscarXcorreo(correo).getClave1().equals(password)) {
                respuesta = "cliente";
            }
        }
        if (empleadoServicio.existeEmpleado(correo).equals("1")) {
            if (empleadoServicio.buscarXmail(correo).getClave1().equals(password)) {
                respuesta = "empleado";
            }
        }
        return respuesta;
    }
}
