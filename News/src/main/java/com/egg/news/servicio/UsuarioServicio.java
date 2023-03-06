
package com.egg.news.servicio;

import com.egg.news.entidad.Periodista;
import com.egg.news.entidad.Rol;
import com.egg.news.entidad.Usuario;
import com.egg.news.exception.MyException;
import com.egg.news.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Damian
 */
@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio ur;
    
    @Transactional
    public void registrar(String nombreUsuario,String password, String password2) throws MyException{
             
        validar(nombreUsuario, password, password2);
        Usuario u = new Usuario();
            u.setNombreUsuario(nombreUsuario);
            u.setPassword(new BCryptPasswordEncoder().encode(password));
            u.setAlta(new Date());
            u.setRol(Rol.USER);
            ur.save(u);

    }
    
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList();
        usuarios = ur.findAll();
    return usuarios;
    }
        
    public Usuario getOne(String id){
    return ur.getOne(id);
    }
    
    @Transactional
    public void crearPeriodista(String id) throws MyException{
             
        Usuario u = getOne(id);
        Periodista p = new Periodista();
        
        p.setId("");
        p.setNombreUsuario(u.getNombreUsuario());
        p.setPassword(u.getPassword());
        p.setAlta(u.getAlta());

        ur.delete(u);
        ur.save(p);
    }
    
    @Transactional
    public void modificarPeriodista(String id, Integer sueldoMensual, Boolean activo) throws MyException{
             
        Usuario u = getOne(id); 
        Periodista p = new Periodista();
        
        p.setId(u.getId());
        p.setNombreUsuario(u.getNombreUsuario());
        p.setPassword(u.getPassword());
        p.setAlta(u.getAlta());
        p.setSueldoMensual(sueldoMensual);
        p.setActivo(activo);
        ur.save(p);
    }
    
    public List<Usuario> listarPeriodistas() {
        List<Usuario> periodistas = new ArrayList();
        periodistas = ur.buscarPeriodistas();
    return periodistas;
    }
       
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException{
        
        Usuario u = ur.buscarNombre(nombreUsuario);
        
        if (u != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + u.getRol().toString());
            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", u);
        return new User(u.getNombreUsuario(), u.getPassword(), permisos);
        }
        return null;
    }
            
    private void validar(String nombreUsuario, String password, String password2) throws MyException{
        
        if (nombreUsuario.isEmpty() || nombreUsuario == null) {
            throw new MyException("El nombre no puede ser nulo o estar vacio");
        }
       
        if (password.isEmpty() || password == null || password.length()<6) {
            throw new MyException("El email no puede ser nulo y debe tener mas de 5 digitos");
        }
        
        if (!password.equals(password2)) {
            throw new MyException("Las contraseñas ingresasdas deben ser iguales");
        }
    }
}
