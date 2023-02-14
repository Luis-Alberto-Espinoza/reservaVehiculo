package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.Empleado;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.entidad.Vehiculo;
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
import java.util.List;

@Controller
@RequestMapping("/vehiculo")//
public class VehiculoControlador {
    @Autowired
    VehiculoServicio vehiculoServicio;
    @Autowired
    ClienteServicio clienteServicio;

    @Autowired
    EmpleadoServicio empleadoServicio;

    @GetMapping("/tablav")

    public String listarAutos(ModelMap modelo) throws Exception {
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        modelo.addAttribute("autos", listaAutos);
        return "autos";

    }



    @GetMapping("/new_auto_1")
    public String new_auto_1(@RequestParam Long id, ModelMap modelo) throws Exception {
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        Empleado empleado = empleadoServicio.findById(id);
        modelo.put("empleadoLog", empleado);
        modelo.addAttribute("autos", listaAutos);
        return "registro_vehiculo.html";

    }

    @PostMapping("/alta")
    public String alta_vehiculo(ModelMap model, Long id, Long ide,
                                @RequestParam String marca, @RequestParam String modelo,
                                @RequestParam String patente, @RequestParam String color,
                                @RequestParam String tipoVehiculo, @RequestParam String cilindradaMotor,
                                @RequestParam String combustible, @RequestParam String typeGama,
                                @RequestParam String precio, @RequestParam String operativo

    ) throws Exception {
        Empleado empleado = empleadoServicio.findById(ide);
        String home = "/empleado/admin/?correo=" + empleado.getMail();

        String titulo1 = "", titulo2 = "", descripcion = "";
        Vehiculo newVehiculo = new Vehiculo();
        newVehiculo.setTipoVehiculo(tipoVehiculo);
        newVehiculo.setMarca(marca);
        newVehiculo.setModelo(modelo);
        newVehiculo.setPatente(patente);
        newVehiculo.setColor(color);
        newVehiculo.setCilindradaMotor(cilindradaMotor);
        newVehiculo.setCombustible(combustible);
        newVehiculo.setTypeGama(typeGama);
        newVehiculo.setPrecio(Double.parseDouble(precio));
        newVehiculo.setOperativo(operativo);
        vehiculoServicio.save(newVehiculo);
        titulo1 = "EXITO!!!!";
        titulo2 = "¡Su gestión fue realizada con éxito !";
        descripcion = "El vehículo fue guardado en la Base de Datos.";
        model.addAttribute("titulo1", titulo1);
        model.addAttribute("titulo2", titulo2);
        model.addAttribute("descripcion", descripcion);
        model.addAttribute("home", home);
        return "exitoGeneral";

    }

    @GetMapping("/delet_vehiculo")
    public String eliminarVehiculo(Long idv, Long ide, ModelMap model) throws Exception {
        System.out.println("llegue a delet vehiculo");

        Empleado empleado = empleadoServicio.findById(ide);
        Vehiculo vehiculo = vehiculoServicio.findById(idv);
        String home = "/empleado/admin/?correo=" + empleado.getMail();

        String titulo1 = "", titulo2 = "", descripcion = "";
        vehiculoServicio.deleteById(idv);
        List<Vehiculo> autos = vehiculoServicio.findAll();
        model.put("autos", autos);
        titulo1 = "EXITO!!!!";
        titulo2 = "¡Su gestión fue realizada con éxito !";
        descripcion = "El vehículo fue eliminado en la Base de Datos.";
        model.addAttribute("titulo1", titulo1);
        model.addAttribute("titulo2", titulo2);
        model.addAttribute("descripcion", descripcion);
        model.addAttribute("home", home);
        return "exitoGeneral";
    }

    @GetMapping("/edit_vehiculo")
    public String editarVehiculo(Long idv, Long ide, ModelMap model) throws Exception {
        Vehiculo vehiculo = vehiculoServicio.findById(idv);
        Empleado empleado = empleadoServicio.findById(ide);
        List<Vehiculo> autos = vehiculoServicio.findAll();
        model.put("vehiculo", vehiculo);
        model.put("empleadoLog", empleado);
        return "editar_vehiculo";
    }
    @PostMapping("/actualizar_vehiculo")
    public String editarVehiculo1(Long idv, Long ide, ModelMap model,
                                   @RequestParam String marca, @RequestParam String modelo,
                                   @RequestParam String patente, @RequestParam String color,
                                   @RequestParam String tipoVehiculo, @RequestParam String cilindradaMotor,
                                   @RequestParam String combustible, @RequestParam String typeGama,
                                   @RequestParam String precio, @RequestParam String operativo
                                   ) throws Exception {
        System.out.println("llegue a edit vehiculo1");
        String titulo1 = "", titulo2 = "", descripcion = "";
        Empleado empleado = empleadoServicio.findById(ide);
        String home = "/empleado/admin/?correo=" + empleado.getMail();

        Vehiculo vehiculo = vehiculoServicio.findById(idv);

        vehiculo.setId(idv);
        vehiculo.setTipoVehiculo(tipoVehiculo);
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setPatente(patente);
        vehiculo.setColor(color);
        vehiculo.setCilindradaMotor(cilindradaMotor);
        vehiculo.setCombustible(combustible);
        vehiculo.setTypeGama(typeGama);
        vehiculo.setPrecio(Double.parseDouble(precio));
        vehiculo.setOperativo(operativo);
        vehiculoServicio.save(vehiculo);
        titulo1 = "EXITO!!!!";
        titulo2 = "Su Gestión fue satisfactoria";
//        descripcion = "Tome Nota del número de Contrato";
        List<Vehiculo> autos = vehiculoServicio.findAll();
        model.put("titulo", "¡Su gestión fue realizada con éxito !");
        model.put("descripcion", "El vehículo fue modificado en la Base de Datos.");
        model.addAttribute("titulo1", titulo1);
        model.addAttribute("titulo2", titulo2);
//        model.addAttribute("descripcion", descripcion);
        model.addAttribute("home", home);
        return "exitoGeneral";
    }

}
