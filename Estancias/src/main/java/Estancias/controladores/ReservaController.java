
package Estancias.controladores;

import Estancias.servicios.ReservaServicio;
import estancias.entidades.Reserva;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Damian
 */

@Controller //Declara un controlador para la gestion de la comunicación usuario/aplicación
@RequestMapping("/reserva") //Mapea la ruta de la petición y el método del controlador
public class ReservaController{   
    
    @Autowired
    private ReservaServicio rs;
    
    @GetMapping("/listar") 
    public String listar(ModelMap modelo){
        
        List<Reserva> reservas = rs.listarReservas();
        modelo.addAttribute("reservas", reservas);
         
    return "index.html";
    }
        
    @GetMapping("/registrar") //asigna solicitudes HTTP GET
    public String registrar(ModelMap modelo){
        
        modelo.put("registrar","pagina de registro");
         
    return "estancia.html";
    }
    
    @PostMapping("/registro") //asigna solicitudes HTTP POST
    public String registroEstancia(@RequestParam String huesped, @RequestParam Date fechaDesde, @RequestParam Date fechaHasta, ModelMap modelo){
        //@RequestParam vincula los parámetros de una petición HTTP a los argumentos de un método
        try {
            rs.altaReserva(huesped,fechaDesde,fechaHasta); 
            modelo.put("exito","Reserva creada con exito");

        }catch(Exception ex){         
            modelo.put("error",ex.getMessage());
            return "redirect:/reserva/registrar";
            
        }return "redirect:/reserva/listar";  
    } 
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        
        try {            
            rs.eliminarReserva(id);
            modelo.put("exito","Reserva borrada con exito");

        }catch(Exception ex){            
            modelo.put("error",ex.getMessage());
            return "redirect:/reserva/listar";

        }return "redirect/reserva/listar";
    }
   
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){

        modelo.put("modificar", rs.getOne(id));

    return "estancia.html";
    }
    
    @PostMapping("/modificacion")
    public String modificacion(@RequestParam String id, @RequestParam Date fechaDesde, @RequestParam Date fechaHasta, ModelMap modelo){
        
        try {
            
            rs.modificarReserva(id,fechaDesde,fechaHasta); 
            modelo.put("exito","Reserva modificada con exito");

        }catch(Exception ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/reserva/modificar";
            
        }return "redirect:/reserva/listar";
    } 
}
