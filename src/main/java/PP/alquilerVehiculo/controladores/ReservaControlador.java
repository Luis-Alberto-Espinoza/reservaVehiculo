
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
    @GetMapping("/generar_reserva")
    public String generar_reserva(ModelMap modelo, @RequestParam Long idv, @RequestParam Long idc) throws Exception {
        Vehiculo auto = vehiculoServicio.findById(idv);
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
        Cliente cliente = clienteServicio.findById(idc);
        Vehiculo auto = vehiculoServicio.findById(idv);
        //System.out.println("59 llegue + fretiro "+ fRetiro);
        List<Date> listadoFechas = new ArrayList<>();
//        listadoFechas.add(fDevolucion);
//        listadoFechas.add(fRetiro);
        Date fechaRegistrp = new Date();
        //     listadoFechas.add(fechaRegistrp);
//        listadoFechas.add(fechaRegistrp);
//        System.out.println("lista fechas --"+ listadoFechas.size()+ "  "+ listadoFechas);

        //////////////
        try {
            reservaServicio.guardarReserva(cliente, auto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /////////////////////////////////
        List<ReservaWeb> vehiculosReservadps = reservaServicio.lDeAutosR(cliente);
        modelo.put("vehiculo", auto);
        modelo.put("clienteLog", cliente);
        modelo.put("autoReservado", vehiculosReservadps);

        return "/hitorial_reserva_cliente";
    }

    @GetMapping("/mis_reservas")
    public String historial(ModelMap modelo, @RequestParam Long idc) throws Exception {
        Cliente cliente = clienteServicio.findById(idc);
        modelo.put("clienteLog", cliente);
        return "/cliente/";
    }
    @GetMapping("/hreservas")
    public String h_reserva(@RequestParam Long id, ModelMap modelo) {
        //http://localhost:9000/reserva/hreservas?id=133
        System.out.println(" llegue a hreservas ");
        Cliente cliente = clienteServicio.buscarPorId(id);
        List<ReservaWeb> autosReservados =  reservaServicio.lDeAutosR(cliente);
        for (int i = 0; i < autosReservados.size(); i++) {
            System.out.println("auto reservado "+i+" "+autosReservados.get(i).getDatosVehiculo().getModelo());
            System.out.println(" listado de reservas "+ autosReservados.get(i).getId());
        }

        modelo.put("autoReservado", autosReservados);
        modelo.put("clienteLog", cliente);

//        modelo.put("vehiculosReservados", autosReservados);
        return "/hitorial_reserva_cliente";
    }
}
