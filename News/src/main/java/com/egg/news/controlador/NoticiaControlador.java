
package com.egg.news.controlador;

import com.egg.news.entidad.Noticia;
import com.egg.news.exception.MyException;
import com.egg.news.servicio.NoticiaServicio;
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
@RequestMapping("/noticia")
public class NoticiaControlador{   
    
    @Autowired
    private NoticiaServicio ns;
    
    @GetMapping("/listar")
    public String listar(ModelMap modelo){
        
        List<Noticia> noticias = ns.listarNoticias();
        modelo.addAttribute("noticias", noticias);
         
    return "index.html";
    }
    
    @GetMapping("/mostrar/{id}")
    public String mostrar(@PathVariable Integer id, ModelMap modelo){

        modelo.addAttribute(ns.getOne(id));

    return "noticia.html";
    }
    
    @GetMapping("/registrar")
    public String registrar(ModelMap modelo){
        
        modelo.put("registrar","pagina de registro");
         
    return "publicar.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String titulo, @RequestParam String cuerpo, @RequestParam String creador, ModelMap modelo){
        
        try {

            ns.crearNoticia(titulo, cuerpo, creador); 
            modelo.put("exito","Noticia cargada con exito");

        }catch(MyException ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/admin/dashboard";
            
        }return "redirect:/admin/dashboard";  
    } 
            
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Integer id, ModelMap modelo){

        modelo.put("noticia", ns.getOne(id));

    return "publicar.html";
    }
    
    @PostMapping("/modificacion")
    public String modificacion(@RequestParam Integer id,@RequestParam String titulo,@RequestParam String cuerpo, ModelMap modelo) throws MyException{
        
        try {
            
            ns.modificarNoticia(id, titulo, cuerpo);
            modelo.put("exito","Noticia modificada con exito");

        }catch(MyException ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/admin/dashboard";
            
        }return "redirect:/admin/dashboard";
    } 
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, ModelMap modelo) throws MyException{
        
        try {
            
            ns.eliminarNoticia(id);
            modelo.put("exito","Noticia borrada con exito");

        }catch(Exception ex){
            
            modelo.put("error",ex.getMessage());
            return "redirect:/admin/dashboard";
            
        }return "redirect:/admin/dashboard";
    }   
}
