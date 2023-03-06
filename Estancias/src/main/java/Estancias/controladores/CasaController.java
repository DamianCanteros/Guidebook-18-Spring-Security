
package Estancias.controladores;

import Estancias.servicios.CasaServicio;
import estancias.entidades.Casa;
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
@RequestMapping("/estancia") //Mapea la ruta de la petición y el método del controlador
public class CasaController{   
    
    @Autowired
    private CasaServicio cs;
    
    @GetMapping("/inicio") 
    public String estancias(ModelMap modelo){
        
        List<Casa> casas = cs.listarCasas();
        modelo.addAttribute("casas", casas);
         
    return "index.html";
    }
    
    @GetMapping("/listar") 
    public String listar(ModelMap modelo){
        
        List<Casa> casas = cs.listarCasas();
        modelo.addAttribute("casas", casas);
         
    return "panelAdmin.html";
    }
        
    @GetMapping("/registrar") //asigna solicitudes HTTP GET
    public String registrar(ModelMap modelo){
        
        modelo.put("registrar","pagina de registro");
         
    return "estancia.html";
    }
    
    @PostMapping("/registro") //asigna solicitudes HTTP POST
    public String registroCasa(@RequestParam String calle, @RequestParam int numero, @RequestParam String codPostal, @RequestParam String ciudad, @RequestParam String pais, 
           @RequestParam Date fechaDesde, @RequestParam Date fechaHasta, @RequestParam int minDias, @RequestParam int maxDias, @RequestParam int precio, @RequestParam String tipoVivienda, ModelMap modelo){
        //@RequestParam vincula los parámetros de una petición HTTP a los argumentos de un método
        try {
            cs.altaCasa(calle,numero,codPostal,ciudad,pais,fechaDesde,fechaHasta,minDias,maxDias,precio,tipoVivienda); 
            modelo.put("exito","Casa registrada con exito");

        }catch(Exception ex){         
            modelo.put("error",ex.getMessage());
            return "redirect:/estancia/registrar";
            
        }return "redirect:/estancia/listar";  
    } 
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        
        try {            
            cs.eliminarCasa(id);
            modelo.put("exito","Casa borrada con exito");

        }catch(Exception ex){            
            modelo.put("error",ex.getMessage());
            return "redirect:/estancia/listar";

        }return "/estancia/listar";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){

        modelo.put("modificar", cs.getOne(id));

    return "estancia.html";
    }
    
    @PostMapping("/modificacion")
    public String modificacion(@RequestParam String id, @RequestParam String calle, @RequestParam int numero, @RequestParam String codPostal, @RequestParam String ciudad, @RequestParam String pais, 
           @RequestParam Date fechaDesde, @RequestParam Date fechaHasta, @RequestParam int minDias, @RequestParam int maxDias, @RequestParam int precio, @RequestParam String tipoVivienda, ModelMap modelo){
        
        try {
            
            cs.modificarCasa(id,calle,numero,codPostal,ciudad,pais,fechaDesde,fechaHasta,minDias,maxDias,precio,tipoVivienda); 
            modelo.put("exito","Casa modificada con exito");

        }catch(Exception ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/estancia/modificar";
            
        }return "redirect:/estancia/listar";
    } 
}
