package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Empleado;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.EmpleadoServicio;
import PP.alquilerVehiculo.servicio.ServicioGeneral;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    ServicioGeneral sg;

    @Autowired
    private EmpleadoServicio empleadoServicio;


    //Método que devolverá el index.html cuando se ingrese a la url raíz de la aplicación
    @GetMapping("/")
    public String index(ModelMap modelo) throws Exception {
        System.out.println("36 index controlador");
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        modelo.addAttribute("autos", listaAutos);
        return "index.html";
    }

//    @RequestMapping("/inicio")
//    public String inicio() {
//        return "inicio";
//    }

    @GetMapping("/login")
//public String login(@RequestParam String correo, @RequestParam String password) {
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        System.out.println("49 controlador login");
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

    //
    //Método que responderá a una petición POST solicitada en la url raíz/registrar y recibirá una serie de argumentos
    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
                            @RequestParam String email, @RequestParam String clave1, @RequestParam String clave2,
                            @RequestParam String direccion, @RequestParam long edad,
                            @RequestParam Integer telefono, @RequestParam Long dni) {

        //Llamamos al método registrar de ClienteServicio y le pasamos los parámetros recibidos por el controlador
        try {
            clienteServicio.registrar(nombre, apellido, email, clave1, clave2, direccion, edad, telefono, dni);
        } catch (Exception ex) {

            //Añadimos el Objeto ModelMap en los parámetros y usamos su método .put() para insertar un valor por pantalla.
            modelo.put("error", ex.getMessage()); // 1ro Nombre de la variable - 2do Valor contenido

            //Seteamos los mismos valores recibidos como argumentos dentro de los inputs del HTML
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("email", email);
            modelo.put("clave1", clave1);
            modelo.put("direccion", direccion);
            modelo.put("edad", edad);
            modelo.put("telefono", telefono);
            modelo.put("dni", dni);

            //Página que va a retornar si algo sale mal
            return "registro.html";
        }

        //Inyectamos textos a los campos de exito.html
        modelo.put("titulo", "¡Bienvenido, encuentre su Auto!");
        modelo.put("descripcion", "Tu usuario ha sido registrado con éxito.");

        //Página que va a retornar si todo sale todo bien
        return "exito.html";
    }

    /*metodo simil LOGIN */
    @PostMapping("/usuarioType")
    public String usuarioType(ModelMap modelo, String correo, String password) throws Exception {
        String respuesta = validadorUsuario(correo, password);
        System.out.println(respuesta);
        if (respuesta.equals("cliente")) {
            Cliente cliente = clienteServicio.buscarXcorreo(correo);
            modelo.put("clienteLog", cliente);
            modelo.addAttribute("correo", cliente.getMail());
            return "/index_cliente";
        }
        if (respuesta.equals("empleado")) {
            Empleado empleado = empleadoServicio.buscarXmail(correo);
            if (empleado.getTypeEmpleado().toLowerCase().equals("ventas")) {
                modelo.put("empleadoLog", empleado);
                return "/index_ventas";
            } else if (empleado.getTypeEmpleado().toLowerCase().equals("gerente")) {
                modelo.put("clienteLog", empleado);
                return "index_gerente";
            } else if (empleado.getTypeEmpleado().toLowerCase().equals("administrador")) {
                modelo.put("clienteLog", empleado);
                return "index_administrador";
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
            System.out.println("cli err");
        }
        if (empleadoServicio.existeEmpleado(correo).equals("1")) {
            if (empleadoServicio.buscarXmail(correo).getClave1().equals(password)) {
                respuesta = "empleado";
            }
            System.out.println("empl err");
        }
        return respuesta;
    }
}
