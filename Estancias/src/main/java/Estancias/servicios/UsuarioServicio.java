
package estancias.servicios;

import estancias.entidades.Rol;
import estancias.entidades.Usuario;
import estancias.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

@Service//recuperar datos relacionados con el usuario
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired //Indica a Spring dónde debe ocurrir una inyección
    private UsuarioRepositorio ur;
    
    @Transactional //se utiliza cada vez que vayamos a hacer una modificación en la BBDD
    public void altaUsuario(String email, String alias, String clave, String clave2, String rol) throws Exception{
             
        validar(alias, clave, clave2);
        Usuario u = new Usuario();

        u.setAlias(alias);
        u.setEmail(email);
        //hashea las contraseñas para que no se guarden en texto plano
        u.setClave(new BCryptPasswordEncoder().encode(clave));
        u.setFechaAlta(new Date());
        u.setRol(Rol.valueOf(rol));
        ur.save(u);

    }
    
    @Transactional
    public void bajaUsuario(String id){
        
        Optional<Usuario> respuesta = ur.findById(id);
        
        if (respuesta.isPresent()) {
            
            Usuario u = respuesta.get();
            u.setFechaBaja(new Date());
        }
    }
    
    @Transactional
    public void eliminarUsuario(String id){
             
        ur.deleteById(id);                

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
    public void modificarUsuario(String id, String alias, String claveActual,String clave,String clave2){
         
        Optional<Usuario> respuesta = ur.findById(id);
        
        if (respuesta.isPresent()) {
            
            Usuario u = respuesta.get();
        
            if (alias.equals(u.getAlias()) && claveActual.equals(u.getClave()) && clave.equals(clave2)) {

                u.setClave(clave);
            }
        }
    }
    
    @Override//se sobreescribe para personalizar el proceso de búsqueda del usuario.
    public UserDetails loadUserByUsername(String alias) throws UsernameNotFoundException {
        
        Usuario u = ur.buscarUsuario(alias);
        
        if (u != null) {
            //la clase GrantedAuthority contiene todos los permisos
            List<GrantedAuthority> permisos = new ArrayList();
            //carga los permisos a quien le digamos
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_USER");
            permisos.add(p);
            //atrapa al usuario registrado (recupera los atributos de la solicitud http)
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            //guarda al usuario en la sesion web
            HttpSession session = attr.getRequest().getSession(true);
            //guarda los atributos que seleccionamos
            session.setAttribute("usuariosession", u);
            //retorna al usuario en un usuario de dominio de Spring Security      
        return new User(u.getAlias(), u.getClave(), permisos);
        }
        return null;
    }
    
        private void validar(String alias, String clave, String clave2) throws Exception{
        
        if (alias.isEmpty() || alias == null) {
            throw new Exception("El nombre no puede ser nulo o estar vacio");
        }
       
        if (clave.isEmpty() || clave == null || clave.length()<6) {
            throw new Exception("El email no puede ser nulo y debe tener mas de 5 digitos");
        }
        
        if (!clave.equals(clave2)) {
            throw new Exception("Las contraseñas ingresasdas deben ser iguales");
        }
    }

}