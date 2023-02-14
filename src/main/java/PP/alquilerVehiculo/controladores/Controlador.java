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
    public String registrar(ModelMap modelo,
                            @RequestParam String nombre, @RequestParam String apellido,
                            @RequestParam String email, @RequestParam String clave1, @RequestParam String clave2,
                            @RequestParam String direccion, @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fNacimiento,
                            @RequestParam Long telefono, @RequestParam Long dni) throws ClienteServiceException {

        System.out.println("llegue al controlador");
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
        System.out.println("entre al controlador"+ correo+ "  "+ password);
        String respuesta = validadorUsuario(correo, password);
        System.out.println(respuesta +" line119 vcontrolador");
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
        System.out.println(clienteServicio.hayCliente(correo));
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
    @GetMapping("/prueba")
    public String pruebaExito(ModelMap model){

        String nombre = "luis";
        String apellido = "apellido";
        String tituloAAA = "/registro";
        String  tituloBBB = "/login";
        String  descripcion3 = "descripcion3 este es el titulo 3";
        String  descripcion2 = "descripcion2 este es el titulo 2";
        int num1 = 14;
        int num2 = 15;
Date fechaActual = new Date();
double numeros = 123345567.25896;
boolean respuesta = true;
List<String> paises= new ArrayList<String>();
paises.add("argentina");
paises.add("chile");
paises.add("venezuela");
paises.add("españa");



model.addAttribute("paises", paises);
model.addAttribute("num1", num1);
model.addAttribute("num2", num2);

model.addAttribute("numeros", numeros);
model.put("fechaactual",fechaActual);
model.put("respuesta", respuesta);

        model.put("nombre", nombre);
        model.put("apellido", apellido);
        model.put("tituloAAA", tituloAAA);
        model.put("titulobbb", tituloBBB);
        model.put("descripcion1", descripcion3);
        model.put("descripcion2", descripcion2);

        return "exitoPrueba";
    }
}
