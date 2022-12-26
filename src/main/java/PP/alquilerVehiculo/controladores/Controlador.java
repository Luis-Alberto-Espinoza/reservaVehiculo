package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.servicio.ClienteServicio;
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

    //Método que devolverá el index.html cuando se ingrese a la url raíz de la aplicación
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/inicio")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/login")
//public String login(@RequestParam String correo, @RequestParam String password) {
public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
//        if (error != null) {
//            model.put("error", "Nombre de Usuario o clave incorrecto");
//        }
//        if (logout != null) {
//            model.put("logout", "Ha salido correctamente de la plataforma.");
//        }
        return "login.html";
    }
//
    @PostMapping("/login12")
    public String login12(@RequestParam String correo, @RequestParam String password) {
        boolean respuesta = false;
        int numero = 0;
        try {
            List<Cliente> clienteList = clienteServicio.findAll();

//            System.out.println("que es :? "+clienteList.get(2).getApellido()+ " \n clave: "+ clienteList.get(2).getClave1());
            for (int i = 0; i < clienteList.size(); i++) {
                //  System.out.println(" elementos iterados " + clienteList.get(i).getClave1());
                if (clienteList.get(i).getClave1().equals(password) & clienteList.get(i).getMail().equals(correo)) {
                         System.out.println("existe el correo " + password);
                    respuesta = true;
                    numero = i;

                } else {
                    //  System.out.println("no existe coincidencias");
                }
            }
            System.out.println(" el id es el " + numero);

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        if (respuesta) {
            return "exito";
        } else {
            return "perfil";
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
}
