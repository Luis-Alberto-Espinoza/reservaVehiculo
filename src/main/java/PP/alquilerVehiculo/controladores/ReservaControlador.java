
package PP.alquilerVehiculo.controladores;

import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.entidad.ReservaWeb;
import PP.alquilerVehiculo.entidad.Vehiculo;
import PP.alquilerVehiculo.servicio.ClienteServicio;
import PP.alquilerVehiculo.servicio.ReservaServicio;
import PP.alquilerVehiculo.servicio.VehiculoServicio;
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

@Controller
@RequestMapping(value = "/reserva")
public class ReservaControlador {
    @Autowired
    ReservaServicio reservaServicio;
    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    VehiculoServicio vehiculoServicio;

//    @GetMapping("/") //esta mal : llega un id que despues lo uso para traer la reserva
//    public String index(ModelMap modelo, @RequestParam Long id) throws Exception {
//        ReservaWeb reserva = reservaServicio.findById(id);
//        modelo.addAttribute("valija", reserva);
//        return "reserva";
//    }

    @GetMapping("/generar_reserva")
    public String generar_reserva(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc) throws Exception {
        Vehiculo auto = vehiculoServicio.findById(idv);
        System.out.println("42 auto.modelo "+ auto.getModelo());
        Cliente cliente = clienteServicio.findById(idc);
        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
        return "reserva";
    }

    @PostMapping("/confi_reserva")
    public String reserva(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc
                          //    ,@RequestParam("fRetiro") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date fRetiro //  @RequestParam @DateTimeFormat(pattern="yyyy/mm/dd") Date fechaRegistrp,
                          //   @RequestParam(value="fDevolucion", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date fDevolucion
    ) throws Exception {
//                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateAndTime

        //ReservaWeb reserva = reservaServicio.findById(1254L);
        Cliente cliente = clienteServicio.findById(idc);
        Vehiculo auto = vehiculoServicio.findById(idv);
        //System.out.println("59 llegue + fretiro "+ fRetiro);
        System.out.println("59 llegue" );
        List<Date> listadoFechas = new ArrayList<>();
//        listadoFechas.add(fDevolucion);
//        listadoFechas.add(fRetiro);
        Date fechaRegistrp = new Date();
   //     listadoFechas.add(fechaRegistrp);
//        listadoFechas.add(fechaRegistrp);
//        System.out.println("lista fechas --"+ listadoFechas.size()+ "  "+ listadoFechas);
       // System.out.println("65 llegue" + reserva.getId());

     //   reservaServicio.prueba(reserva);
       // funciona//  reservaServicio.pruebaD(cliente);
        System.out.println(reservaServicio.lDeAutosR(cliente).size());

        System.out.println("69 llegue" );

        /////////////////////////////////
        List<ReservaWeb> vehiculosReservadps = new ArrayList<>();
        vehiculosReservadps = reservaServicio.lDeAutosR(cliente);
//        vehiculo.add(auto);buscarAutosXclienteXreserva
//        vehiculos= reservaServicio.lDeAutosR(cliente);
//        System.out.println(" 70 RC esto tiene vehiculos "+ vehiculos.toString()+ "  "+vehiculos);

        //////////////
        try {
            reservaServicio.guardarReserva(cliente, auto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
        modelo.put("autoReservado", vehiculosReservadps);
//        modelo.addAllAttributes(vehiculosReservadps);


        //persistir los datos
        return "/hitorial_reserva_cliente";
    }
    @GetMapping("/mis_reservas")
    public String historial(ModelMap modelo, @RequestParam Long idc) throws Exception {
        System.out.println("79 llegue");
        Cliente cliente = clienteServicio.findById(idc);

        modelo.put("clienteLog", cliente);
        return "/cliente/";
    }


}
