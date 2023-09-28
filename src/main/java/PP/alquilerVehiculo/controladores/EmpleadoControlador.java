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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.http.HttpClient;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoControlador {
    @Autowired
    private EmpleadoServicio empleadoServicio;

    @Autowired
    private VehiculoServicio vehiculoServicio;

    @Autowired
    private ClienteServicio clienteServicio;


    @GetMapping("/admin")
    public String adminHome(@RequestParam String correo, ModelMap modelo) throws Exception {
        Empleado usuario = empleadoServicio.buscarXmail(correo);
        modelo.addAttribute("empleadoLog", usuario);
        return "index_admin";
    }


    @GetMapping("/admin_p")
    //http://localhost:9000/cliente/?correo=Correocliente_14%40correo.com&password=123456
    public String admin_persona(@RequestParam Long ide, ModelMap modelo) throws Exception {
        List<Empleado> empleados = empleadoServicio.findAll();
        modelo.put("emleado", empleados);
        Empleado usuario = empleadoServicio.findById(ide);
        modelo.put("empleadoLog", usuario);
        return "admin_p";
    }

    @GetMapping("/delet_empleado")
    public String eliminarVehiculo(Long idm, Long ide, RedirectAttributes redirectAttrs) throws Exception {
        empleadoServicio.deleteById(idm);
        redirectAttrs.addAttribute("correo", empleadoServicio.findById(ide).getId());
        return "redirect:/empleado/admin_p/?ide={correo}";
    }

    @GetMapping("/new_empleado_1")//ide=2009
    public String new_empleado_1(@RequestParam Long ide, ModelMap modelo) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
        modelo.put("empleadoLog", empleado);
        return "new_empleado1";
    }

    @GetMapping("/new_empleado_2")//?ide=2009&dni=29148574
    public String new_empleado_2(@RequestParam Long ide, String dni, ModelMap modelo) throws Exception {
        Empleado empleadoLog = empleadoServicio.findById(ide);

        if (clienteServicio.hayClienteXdni(dni).equals("1")) {
            Cliente clienteNew = clienteServicio.buscarXdni(Long.parseLong(dni));
            modelo.addAttribute("empleadoLog", empleadoLog);
            modelo.put("usuario", clienteNew);
            modelo.addAttribute("formularioTipo", "Alta");
        } else if (empleadoServicio.existeEmpleadoXdni(dni).equals("1")) {
            modelo.addAttribute("empleadoLog", empleadoLog);
            Empleado empleadoNew = empleadoServicio.buscarXdni(dni);
            modelo.put("usuario", empleadoNew);
            modelo.addAttribute("formularioTipo", "Editar");
        } else {
            modelo.addAttribute("empleadoLog", empleadoLog);
            modelo.put("usuario", null);
            modelo.addAttribute("formularioTipo", "Alta");
            return "registrar_empleado";
        }
        return "new_empleado2";
    }

    @PostMapping("/new_empleado_3")
    public String saveNewEmpleado(ModelMap modelo, Long ide,
                                  @RequestParam String nombre, @RequestParam String apellido,
                                  @RequestParam String email, @RequestParam String clave1,
                                  @RequestParam String direccion, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fNacimiento,
                                  @RequestParam Long telefono, @RequestParam Long dni, @RequestParam String typeEmpleado) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
        String titulo1 = "", titulo2 = "", descripcion = "";
        String hayEmpleado = empleadoServicio.existeEmpleado(email);
        Empleado newEmpleado;
        if (hayEmpleado.equals("0")) {
            newEmpleado = new Empleado();
            titulo2 = "El empleado fue creado";
            LocalDate fechaRegistro = LocalDate.now();
            newEmpleado.setAlta(fechaRegistro);
        } else {
            newEmpleado = empleadoServicio.buscarXdni(String.valueOf(dni));
            titulo2 = "El empleado fue actualizado";
        }
        newEmpleado.setNombre(nombre);
        newEmpleado.setApellido(apellido);
        newEmpleado.setMail(email);
        newEmpleado.setClave1(clave1);
        newEmpleado.setDireccion(direccion);
        newEmpleado.setFechaNacimiento(fNacimiento);
        newEmpleado.setTelefono(telefono);
        newEmpleado.setDni(dni);
        newEmpleado.setTypeEmpleado(typeEmpleado);
        Empleado empleadoGuarado = empleadoServicio.save(newEmpleado);
        titulo1 = "EXITO!!!!";
        descripcion = "Tome Nota del número de empleado";
        modelo.addAttribute("titulo1", titulo1);
        modelo.addAttribute("titulo2", titulo2);
        modelo.addAttribute("descripcion", descripcion);
        String home = "/empleado/admin/?correo=" + empleado.getMail();
        modelo.addAttribute("home", home);
        modelo.put("numero", empleadoServicio.buscarXdni(String.valueOf(dni)).getId());
        return "/exitoGeneral";
    }

    @GetMapping("/edit_empleado")
    public String editarVehiculoEmpleado(Long idm, Long ide, ModelMap model) throws Exception {
        Empleado aModificar = empleadoServicio.findById(idm);
        Empleado empleado = empleadoServicio.findById(ide);
        model.addAttribute("formularioTipo", "Editar");
        model.put("usuario", aModificar);
        model.put("empleadoLog", empleado);
        return "new_empleado2";
    }

    @GetMapping("/admin_v")
    //http://localhost:9000/cliente/?correo=Correocliente_14%40correo.com&password=123456
    public String admin_vehiculo(@RequestParam Long id, ModelMap modelo) throws Exception {
        Empleado empleado = empleadoServicio.findById(id);
        List<Vehiculo> vehiculo = vehiculoServicio.findAll();
        modelo.put("empleadoLog", empleado);
        modelo.put("autos", vehiculo);
        return "admin_v";
    }

    @GetMapping("/ventas")
    //http://localhost:9000/cliente/?correo=Correocliente_14%40correo.com&password=123456
    public String ventasHome(@RequestParam String correo, ModelMap modelo) throws Exception {
        Empleado usuario = empleadoServicio.buscarXmail(correo);
        modelo.addAttribute("empleadoLog", usuario);
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
    public void LEVentas() throws Exception {
        Empleado empleado = empleadoServicio.buscarXmail("CorreoEmpleado_09@correo.com");
        System.out.println(empleado.getId() + " id " + empleado.getTypeEmpleado() + "  " + empleado.getNombre());
    }
}
