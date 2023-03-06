
package Estancias.controladores;

import Estancias.servicios.ClienteServicio;
import Estancias.servicios.FamiliaServicio;
import estancias.entidades.Usuario;
import estancias.servicios.UsuarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/usuario") //Mapea la ruta de la petición y el método del controlador
public class UsuarioController{   
    
    @Autowired
    private UsuarioServicio us;
    
    @Autowired
    private FamiliaServicio fs;
        
    @Autowired
    private ClienteServicio cs;
    
//    @GetMapping("/listar") 
//    public String listar(ModelMap modelo){
//        
//        List<Usuario> usuarios = us.listarUsuarios();
//        modelo.addAttribute("usuarios", usuarios);
//         
//    return "panelAdmin.html";
//    }
        
    @GetMapping("/registrar") //asigna solicitudes HTTP GET
    public String registrar(ModelMap modelo){
        
        modelo.put("registrar","pagina de registro");
         
    return "portalFormularios.html";
    }
      
    @PostMapping("/registro") //asigna solicitudes HTTP POST
    public String registro(@RequestParam String email, @RequestParam String alias, @RequestParam String clave, @RequestParam String clave2, @RequestParam String rol, ModelMap modelo){
        //@RequestParam vincula los parámetros de una petición HTTP a los argumentos de un método
        try {
            us.altaUsuario(email,alias,clave,clave2,rol); 
            modelo.put("exito","Usuario registrado con exito");
        }catch(Exception ex){         
            modelo.put("error",ex.getMessage());
            return "redirect:/usuario/registrar";
            
        }return "redirect:/usuario/login";  
    } 

    @GetMapping("/modificar/{email}")
    public String modificar(@PathVariable String email, ModelMap modelo){

        modelo.put("modificar", us.getOne(email));

    return "portalFormularios.html";
    }
    
    @PostMapping("/modificacion")
    public String modificacion(@RequestParam String id, @RequestParam String alias, @RequestParam String claveActual, @RequestParam String clave, @RequestParam String clave2, ModelMap modelo){
                      
        try {
            
            us.modificarUsuario(id,alias,claveActual,clave,clave2); 
            modelo.put("exito","Clave modificada con exito");

        }catch(Exception ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/usuario/modificar";
            
        }return "redirect:/usuario/listar";
    } 
    
//    @GetMapping("/baja/{id}")
//    public String baja(@PathVariable String id, ModelMap modelo) {
//        
//        try {            
//            us.bajaUsuario(id);
//            modelo.put("exito","baja de usuario exitosa");
//
//        }catch(Exception ex){            
//            modelo.put("error",ex.getMessage());
//            return "redirect:../admin/usuario";
//            
//        }return "redirect:../admin/usuario";
//    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        
        try {            
            us.eliminarUsuario(id);
            modelo.put("exito","Usuario borrado con exito");

        }catch(Exception ex){            
            modelo.put("error",ex.getMessage());
            return "redirect:../usuario/listar";

        }return "redirect:/usuario/registrar";
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        
        if (error != null) {
            modelo.put("error","usuario o contraseña invalidos");
        return "redirect:/usuario/login";
        }        
    return "portalFormularios.html";
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session){
        
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        
        if (fs.getOne(logueado.getEmail()) == null && cs.getOne(logueado.getEmail()) == null){
            
            return "redirect:../usuario/registrar";
        }
                
    return "redirect:../usuario/inicio";
    }
    
    
}
