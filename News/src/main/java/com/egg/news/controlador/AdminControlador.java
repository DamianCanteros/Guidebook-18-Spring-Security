
package com.egg.news.controlador;

import com.egg.news.entidad.Noticia;
import com.egg.news.entidad.Usuario;
import com.egg.news.exception.MyException;
import com.egg.news.servicio.NoticiaServicio;
import com.egg.news.servicio.UsuarioServicio;
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
@Controller 
@RequestMapping("/admin")
public class AdminControlador {
      
    @Autowired
    private NoticiaServicio ns;
    
    @Autowired
    private UsuarioServicio us;
    
    @GetMapping("/dashboard")
    public String panelAdmin(ModelMap modelo){
        
        List<Noticia> noticias = ns.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        
    return "panelAdmin.html";
    }
    
    @GetMapping("/usuarios")
    public String usuarios(ModelMap modelo){
        
        List<Usuario> usuarios = us.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        
    return "panelAdmin.html";
    }
    
    //--------------------------------------------------------------------------------------------------
    
    @GetMapping("/crear/{id}")
    public String crearPeriodista(@PathVariable String id, ModelMap modelo) throws MyException{
        
        try {
            us.crearPeriodista(id);
            modelo.put("exito","Periodista creado con exito");

        }catch(MyException ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/admin/usuarios";
            
        }return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/periodistas")
    public String periodistas(ModelMap modelo){
        
        List<Usuario> periodistas = us.listarPeriodistas();
        modelo.addAttribute("periodistas", periodistas);
        
    return "panelAdmin.html";
    }
    
    @PostMapping("/modificar")
    public String modificarPeriodista(@RequestParam String id,@RequestParam Boolean activo,@RequestParam Integer sueldoMensual, ModelMap modelo) throws MyException{
        
        try {
            
            us.modificarPeriodista(id, sueldoMensual, activo);
            modelo.put("exito","Periodista modificado con exito");

        }catch(MyException ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/admin/periodistas";
            
        }return "redirect:/admin/periodistas";
    }
 
}
