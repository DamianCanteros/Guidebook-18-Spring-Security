
package com.egg.news.controlador;

import com.egg.news.entidad.Usuario;
import com.egg.news.exception.MyException;
import com.egg.news.servicio.UsuarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Damian
 */
@Controller 
@RequestMapping("/portal")
public class PortalControlador{   

    @Autowired
    private UsuarioServicio us;
    
    @GetMapping("/registrar")
    public String registrar(ModelMap modelo){
        
        modelo.put("registrar","pagina de registro");
         
    return "formulario.html";
    }
        
    @PostMapping("/registro")
    public String registro(@RequestParam String nombreUsuario, @RequestParam String password, @RequestParam String password2, ModelMap modelo){
        
        try {

            us.registrar(nombreUsuario, password, password2);
            modelo.put("exito","Usuario registrado con exito");

        }catch(MyException ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/portal/registrar";
            
        }return "redirect:/portal/login";  
    } 
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error,ModelMap modelo){
        
        if (error != null) {
            modelo.put("error","usuario o contraseña invalidos");
        return "redirect:/portal/login";
        }        
    return "formulario.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PERIODISTA', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session){
        
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        
        if (logueado.getRol().toString().equals("ADMIN") || logueado.getRol().toString().equals("PERIODISTA")) {
                return "redirect:/admin/dashboard";
        }
                
    return "redirect:../noticia/listar";
    }
    
}
