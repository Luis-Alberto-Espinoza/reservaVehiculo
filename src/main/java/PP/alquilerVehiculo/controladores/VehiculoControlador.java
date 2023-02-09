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

    @GetMapping("/resEmp")
    public String listarAutosReserva(ModelMap modelo, @RequestParam long dni, @RequestParam Long ide,
                                     @RequestParam("fRetiro") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fRetiro,
                                     @RequestParam(value = "fDevolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fDevolucion
    ) throws Exception {
        System.out.println("35 de VehiculoControlador " + fRetiro + "  -  " + fDevolucion);
        Empleado empleado = empleadoServicio.findById(ide);
        List<Vehiculo> listaAutos = vehiculoServicio.findAll();
        Cliente cliente = clienteServicio.buscarXdni(dni);
        System.out.println("38 line cliente " + cliente);
        modelo.put("fRetiro", fRetiro);
        modelo.put("fDevolucion", fDevolucion);
        modelo.put("empleadoLog", empleado);
        modelo.addAttribute("autos", listaAutos);
        modelo.put("clienteLog", cliente);
        return "autos_reserva";

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
    public String alta_vehiculo(ModelMap model, Long id,
                                @RequestParam String marca, @RequestParam String modelo,
                                @RequestParam String patente, @RequestParam String color,
                                @RequestParam String tipoVehiculo, @RequestParam String cilindradaMotor,
                                @RequestParam String combustible, @RequestParam String typeGama,
                                @RequestParam String precio, @RequestParam String operativo

    ) throws Exception {
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
        model.put("titulo", "¡Su gestión fue realizada con éxito !");
        model.put("descripcion", "El vehículo fue guardado en la Base de Datos.");
        return "exito";
    }

    @GetMapping("/delet_vehiculo")
    public String eliminarVehiculo(Long idv, Long ide, ModelMap modelo) throws Exception {
        System.out.println("llegue a delet vehiculo");
        Empleado empleado = empleadoServicio.findById(ide);
        Vehiculo vehiculo = vehiculoServicio.findById(idv);
        vehiculoServicio.deleteById(idv);
        List<Vehiculo> autos = vehiculoServicio.findAll();
        modelo.put("autos", autos);
        modelo.put("empleadoLog.id", empleado.getId());
//        modelo.addAttribute("id_empleado" )
        return "redirect:/admin_p/{empleadoLog.id}";
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
        List<Vehiculo> autos = vehiculoServicio.findAll();
        model.put("titulo", "¡Su gestión fue realizada con éxito !");
        model.put("descripcion", "El vehículo fue modificado en la Base de Datos.");
        return "exito";
    }

}
