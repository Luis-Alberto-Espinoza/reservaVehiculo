package PP.alquilerVehiculo.controladores;

//
//@Controller // Le indica a Spring que es un componente 'Controlador'
//@RequestMapping("/") // Indica cual es la url que va a escuchar este controlador (desde la raíz, en este caso)
public class PortalControlador {
//
//    //ATRIBUTO - Usuario Service
//    @Autowired
//    private ClienteServicio clienteServicio;
//
//    //Método que devolverá el index.html cuando se ingrese a la url raíz de la aplicación
//    @GetMapping("/")
//    public String index() {
//        return "index.html";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login.html";
//    }
//
//    @GetMapping("/registro")
//    public String registro() {
//        return "registro.html";
//    }
//
//    @PostMapping("/registrar")
//    public String registrar(
//                            @RequestParam String nombre, @RequestParam String apellido,
//                            @RequestParam String direccion, @RequestParam Integer edad,
//                            @RequestParam long dni, @RequestParam long telefono,
//                            @RequestParam String email, @RequestParam String clave1,
//                            @RequestParam String clave2) {
//
//        if (clave1.equals(clave2)){
//        //Llamamos al método registrar de UsuarioService y le pasamos los parámetros recibidos por el controlador
//        try {
//          //  clienteServicio.registrar(nombre, apellido, dni,  telefono, edad, direccion,  email, clave1, clave2);
//        } catch (Error e) {
///*
//            //Añadimos el Objeto ModelMap en los parámetros y usamos su método .put() para insertar un valor por pantalla.
//            modelo.put("error", ex.getMessage()); // 1ro Nombre de la variable - 2do Valor contenido
//
//            //Seteamos los mismos valores recibidos como argumentos dentro de los inputs del HTML
//            modelo.put("nombre", nombre);
//            modelo.put("apellido", apellido);
//            modelo.put("email", email);
//            modelo.put("clave1", clave1);
//            modelo.put("clave2", clave2);
//
//            //Nos traemos todas las zonas de la base de datos
//            List<Zona> zonas = zonaService.listarZonas();
//            //Inyectamos en el ModelMap el listado con el key 'zonas'
//            modelo.put("zonas", zonas);
//
//            */
//            //Página que va a retornar si algo sale mal
//                        return "registro.html";
//
//        }
//        return "index.html";
//
//        } else  {
//            return "registro.html";
//        }
//    }
}