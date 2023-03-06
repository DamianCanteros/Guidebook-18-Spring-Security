
package com.egg.news.servicio;

import com.egg.news.entidad.Noticia;
import com.egg.news.entidad.Periodista;
import com.egg.news.entidad.Rol;
import com.egg.news.entidad.Usuario;
import com.egg.news.exception.MyException;
import com.egg.news.repositorio.NoticiaRepositorio;
import com.egg.news.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author Damian
 */
@Service
public class NoticiaServicio{
    
    @Autowired
    private NoticiaRepositorio nr;
    
    @Autowired
    private UsuarioRepositorio ur;
  
    @Transactional
    public void crearNoticia(String titulo, String cuerpo, String creador) throws MyException{
             
        Usuario u = ur.buscarNombre(creador);
        validar(titulo,cuerpo);
        Noticia n = new Noticia();
        
        n.setTitulo(titulo);
        n.setCuerpo(cuerpo);
        n.setAlta(new Date());
        
        if (u.getRol() == Rol.PERIODISTA) {
            
            Periodista p = new Periodista();
            p.setId(u.getId());

            n.setCreador(p);
        }
        nr.save(n);
    }
    
    @Transactional
    public void modificarNoticia(Integer id, String titulo, String cuerpo) throws MyException{
        
        validar(titulo,cuerpo);   
        Optional<Noticia> respuesta = nr.findById(id);
        
        if (respuesta.isPresent()) {
            
            Noticia n = respuesta.get();
            n.setTitulo(titulo);
            n.setCuerpo(cuerpo);
        }
    }
    
    @Transactional
    public void eliminarNoticia(Integer id){
             
            nr.deleteById(id);                

    }
    
    public List<Noticia> listarNoticias() {
        List<Noticia> noticias = new ArrayList();
        noticias = nr.findAll();
        return noticias;
    }
    
    public Noticia getOne(Integer id){
        return nr.getOne(id);
    }
    
    private void validar(String titulo, String cuerpo) throws MyException{
        
        if (titulo.isEmpty() || titulo == null) {
            throw new MyException("El titulo no puede ser nulo o estar vacio");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MyException("El titulo no puede ser nulo o estar vacio");
        }
    }
}
