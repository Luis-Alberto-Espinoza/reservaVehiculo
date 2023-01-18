package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Empleado;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.EmpleadoServicio;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
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

    //
    @PostMapping("/login12")
    public void login12(@RequestParam String correo, @RequestParam String password, ModelMap modelo) {
        System.out.println("62 controlador login post");
        System.out.println("correo " + correo);
        System.out.println("password " + password);
        boolean respuesta = false;
        int numero = 0;
        try {
            List<Cliente> clienteList = clienteServicio.findAll();


            for (int i = 0; i < clienteList.size(); i++) {
                if (clienteList.get(i).getClave1().equals(password) & clienteList.get(i).getMail().equals(correo)) {
//                    System.out.println("existe el correo " + clienteList.get(i).getMail());
                    respuesta = true;
                    numero = i;


                } else {
                    //  System.out.println("no existe coincidencias "+ clienteList.get(i).getMail());
                }
            }
            System.out.println(" el id es el " + numero);

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        if (respuesta) {
            System.out.println("pase por la 85");
            modelo.put("correo", correo);
            // return "index_cliente";
        } else {
            // return "/";
        }

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

    @PostMapping("/prueba")
    public String usuarioType(ModelMap modelo, String correo) throws Exception {
        System.out.println("llegue a usuarioType ");
        System.out.println("correo de entrada " + correo);
//        String correo = "CorreoEmpleado_07@correo.com";// ventas
//        String correo = "CorreoEmpleado_01@correo.com"; //gerente
//        String correo = "CorreoEmpleado_04@correo.com"; //administrador
        // String correo = "Correocliente_11@correo.com"; // cliente

        if (clienteServicio.buscarXcorreo(correo) != null) {
            Cliente cliente = clienteServicio.buscarXcorreo(correo);
            modelo.put("clienteLog", cliente);
            List<Vehiculo> listVehiculos = vehiculoServicio.findAll();
            modelo.addAttribute("autos", listVehiculos);
            modelo.addAttribute("correo", cliente.getMail());
            return "/index_cliente";
        } else if (empleadoServicio.buscarXmail(correo) != null) {
            Empleado empleado = empleadoServicio.buscarXmail(correo);
            System.out.println((empleado.getTypeEmpleado()));
            if (empleado.getTypeEmpleado().toLowerCase().equals("ventas")) {
                modelo.put("clienteLog", empleado);
                return "index_ventas";
            } else if (empleado.getTypeEmpleado().toLowerCase().equals("gerente")) {
                modelo.put("clienteLog", empleado);
                return "index_gerente";
            } else if (empleado.getTypeEmpleado().toLowerCase().equals("administrador")) {
                modelo.put("clienteLog", empleado);
                return "index_administrador";
            }
        }
//       if( Cliente cliente = clienteServicio.buscarXcorreo(correo);
        // Empleado empleado= empleadoServicio.buscarXmail(correo);
        return "login";
    }
}
