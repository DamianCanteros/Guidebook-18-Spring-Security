
package Estancias.controladores;

import Estancias.servicios.ClienteServicio;
import estancias.entidades.Cliente;
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
@RequestMapping("/cliente") //Mapea la ruta de la petición y el método del controlador
public class ClienteController{   
    
    @Autowired
    private ClienteServicio cs;
    
    @GetMapping("/registrar") //asigna solicitudes HTTP GET
    public String registrar(ModelMap modelo){
        
        modelo.put("registrar","pagina de registro");
         
    return "usuario.html";
    }
            
    @PostMapping("/registro") //asigna solicitudes HTTP POST
    public String registroCliente(@RequestParam String nombre, @RequestParam String calle, @RequestParam int numero, @RequestParam String codPostal, 
           @RequestParam String ciudad, @RequestParam String pais, String email, ModelMap modelo){
        //@RequestParam vincula los parámetros de una petición HTTP a los argumentos de un método
        try {
            cs.altaCliente(nombre,calle,numero,codPostal,ciudad,pais,email); 
            modelo.put("exito","Usuario registrado con exito");

        }catch(Exception ex){         
            modelo.put("error",ex.getMessage());
            return "redirect:/cliente/registrar";
            
        }return "redirect:../casa/listar";  
    } 
    
    @GetMapping("/listar")
    public String listar(ModelMap modelo){
        
        List<Cliente> clientes = cs.listarClientes();
        modelo.addAttribute("clientes", clientes);
         
    return "panelAdmin.html";
    }
    
    @GetMapping("/modificar/{email}")
    public String modificar(@PathVariable String email, ModelMap modelo){

        modelo.put("modificar", cs.getOne(email));

    return "usuario.html";
    }
    
    @PostMapping("/modificacion")
    public String modificacion(@RequestParam String id, @RequestParam String nombre, @RequestParam String calle, @RequestParam int numero, @RequestParam String codPostal, 
           @RequestParam String ciudad, @RequestParam String pais, String email, ModelMap modelo){
        
        try {
            
            cs.modificarCliente(id,nombre,calle,numero,codPostal,ciudad,pais,email); 
            modelo.put("exito","Cliente modificado con exito");

        }catch(Exception ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/cliente/modificar";
            
        }return "redirect:/cliente/listar";
    } 
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo){
        
        try {
            
            cs.eliminarCliente(id);
            modelo.put("exito","Cliente borrado con exito");

        }catch(Exception ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/cliente/listar";
            
        }return "redirect:/cliente/listar";
    } 
}
