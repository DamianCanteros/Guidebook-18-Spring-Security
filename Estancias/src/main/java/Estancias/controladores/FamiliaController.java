
package estancias.controladores;

import Estancias.servicios.FamiliaServicio;
import estancias.entidades.Familia;
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
@RequestMapping("/familia") //Mapea la ruta de la petición y el método del controlador
public class FamiliaController{   

    @Autowired
    private FamiliaServicio fs;
    
    @GetMapping("/registrar") //asigna solicitudes HTTP GET
    public String registrar(ModelMap modelo){
        
        modelo.put("registrar","pagina de registro");
         
    return "usuario.html";
    }
           
    @PostMapping("/registro") //asigna solicitudes HTTP POST
    public String registroFamilia(@RequestParam String nombre, @RequestParam int edadMin, @RequestParam int edadMax, @RequestParam int numHijos, @RequestParam String email, ModelMap modelo){
        //@RequestParam vincula los parámetros de una petición HTTP a los argumentos de un método
        try {
            fs.altaFamilia(nombre,edadMin,edadMax,numHijos,email); 
            modelo.put("exito","Usuario registrado con exito");

        }catch(Exception ex){         
            modelo.put("error",ex.getMessage());
            return "redirect:/familia/registrar";
            
        }return "redirect:../casa/listar";  
    }
    
    @GetMapping("/listar")
    public String listar(ModelMap modelo){
        
        List<Familia> familias = fs.listarFamilias();
        modelo.addAttribute("familias", familias);
         
    return "panelAdmin.html";
    }
    
    @GetMapping("/modificar/{email}")
    public String modificar(@PathVariable String email, ModelMap modelo){

        modelo.put("modificar", fs.getOne(email));

    return "usuario.html";
    }
    
    @PostMapping("/modificacion")
    public String modificacion(@RequestParam String id, @RequestParam String nombre, @RequestParam int edadMin, @RequestParam int edadMax, @RequestParam int numHijos, @RequestParam String email, ModelMap modelo){
        
        try {
            
            fs.modificarFamilia(id,nombre,edadMin,edadMax,numHijos,email); 
            modelo.put("exito","Familia modificada con exito");

        }catch(Exception ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/familia/modificar";
            
        }return "redirect:/familia/listar";
    } 
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo){
        
        try {
            
            fs.eliminarFamilia(id);
            modelo.put("exito","Familia borrada con exito");

        }catch(Exception ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/familia/listar";
            
        }return "redirect:/familia/listar";
    } 

         
}
