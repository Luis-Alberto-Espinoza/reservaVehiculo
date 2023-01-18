package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.*;
import PP.alquilerVehiculo.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/contrato")

@Controller
public class ContratoControlador {
    @Autowired
    ReservaServicio reservaServicio;
    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    VehiculoServicio vehiculoServicio;
    @Autowired
    ContratoServicio contratoServicio;
    @Autowired
    EmpleadoServicio empleadoServicio;

    @GetMapping("/")
    public String index_contrato(ModelMap modelo) throws Exception {
        return "indexe";
    }
    @GetMapping("/generar_contrato")
    public String generar_contrato(ModelMap modelo) throws Exception {
//    public String generar_contrato(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc) throws Exception {
        long idv = 108;
        long idc = 127;
        long idcon = 1255;
        //Vehiculo auto = vehiculoServicio.findById(idv);
        //Cliente cliente = clienteServicio.findById(idc);
        ReservaWeb reservaWeb = reservaServicio.findById(idcon);
        System.out.println(reservaWeb.getId());
        Cliente cliente = reservaWeb.getCliente();
        Vehiculo auto = reservaWeb.getDatosVehiculo();
        modelo.put("datoReserva", reservaWeb);
        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
        return "genera_contrato";
    }

    @PostMapping("/confi_contrato")
//    public String reserva(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc
    public String reserva(ModelMap modelo, @RequestParam Long id_reserva
                          //    ,@RequestParam("fRetiro") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date fRetiro //  @RequestParam @DateTimeFormat(pattern="yyyy/mm/dd") Date fechaRegistrp,
                          //   @RequestParam(value="fDevolucion", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date fDevolucion
    ) throws Exception {
        long idEmpleado = 11116;
        System.out.println("62 idreserva " +id_reserva);

        ReservaWeb reserva = reservaServicio.findById(id_reserva);
        Empleado empleado =  empleadoServicio.findById(idEmpleado);
        System.out.println("66 >===");
        System.out.println(empleado.getId()+"  67///////");
//        Contrato contrato = contratoServicio.findById(idcontrato);/
//        Cliente cliente = clienteServicio.findById(idc);
//        Vehiculo auto = vehiculoServicio.findById(idv);
        //System.out.println("59 llegue + fretiro "+ fRetiro);
        List<Date> listadoFechas = new ArrayList<>();
//        listadoFechas.add(fDevolucion);
//        listadoFechas.add(fRetiro);
        Date fechaRegistrp = new Date();
        //     listadoFechas.add(fechaRegistrp);
//        listadoFechas.add(fechaRegistrp);
//        System.out.println("lista fechas --"+ listadoFechas.size()+ "  "+ listadoFechas);

        //////////////
        System.out.println(empleado.getApellido()+ " 79 emple ape");
            contratoServicio.guardarContrato(reserva, empleado);
        System.out.println("despues de guardar el contrato");
        /////////////////////////////////
        Vehiculo auto = reserva.getDatosVehiculo();
        Cliente cliente = reserva.getCliente();

//        List<ReservaWeb> vehiculosReservadps = reservaServicio.lDeAutosR(cliente);
        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
//        modelo.put("autoReservado", vehiculosReservadps);
        System.out.println("92 line fin de la funcion ");
        return "/hitorial_contrato";
    }
}
