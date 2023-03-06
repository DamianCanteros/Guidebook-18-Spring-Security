
package Estancias.servicios;

import estancias.entidades.Cliente;
import estancias.repositorios.ClienteRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 * @author Damian
 */

@Service//recuperar datos relacionados con el usuario
public class ClienteServicio {
    
    @Autowired //Indica a Spring dónde debe ocurrir una inyección
    private ClienteRepositorio cr;
    
    @Transactional //se utiliza cada vez que vayamos a hacer una modificación en la BBDD
    public void altaCliente(String nombre, String calle, int numero, String codPostal, String ciudad, String pais, String email) throws Exception{
             
        Cliente c = new Cliente();

        c.setNombre(nombre);
        c.setCalle(calle);
        c.setNumero(numero);
        c.setCodPostal(codPostal);
        c.setCiudad(ciudad);
        c.setPais(pais);
        c.setEmail(email);
        cr.save(c);

    }
    
    @Transactional
    public void eliminarCliente(String id){
             
        cr.deleteById(id);                

    }
    
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList();
        clientes = cr.findAll();
        return clientes;
    }
    
    public Cliente getOne(String email){
        return cr.buscarEmail(email);
    }

    @Transactional
    public void modificarCliente(String id, String nombre, String calle, int numero, String codPostal, String ciudad, String pais, String email){
         
        Optional<Cliente> respuesta = cr.findById(id);
        
        if (respuesta.isPresent()) {
            
            Cliente c = respuesta.get();
            c.setNombre(nombre);
            c.setCalle(calle);
            c.setNumero(numero);
            c.setCodPostal(codPostal);
            c.setCiudad(ciudad);
            c.setPais(pais);
            c.setEmail(email);
        }
    } 
}
