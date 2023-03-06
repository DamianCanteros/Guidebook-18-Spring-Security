
package Estancias.servicios;

import estancias.entidades.Familia;
import estancias.repositorios.FamiliaRepositorio;
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
public class FamiliaServicio {
    
    @Autowired //Indica a Spring dónde debe ocurrir una inyección
    private FamiliaRepositorio fr;
    
    @Transactional //se utiliza cada vez que vayamos a hacer una modificación en la BBDD
    public void altaFamilia(String nombre, int edadMin, int edadMax, int numHijos, String email){
             
        Familia f = new Familia();

        f.setNombre(nombre);
        f.setEdadMin(edadMin);
        f.setEdadMax(edadMax);
        f.setNumHijos(numHijos);
        f.setEmail(email);
        fr.save(f);

    }
    
    @Transactional
    public void eliminarFamilia(String id){
             
        fr.deleteById(id);                

    }
    
    public List<Familia> listarFamilias() {
        List<Familia> familias = new ArrayList();
        familias = fr.findAll();
        return familias;
    }
    
    public Familia getOne(String email){
        return fr.buscarEmail(email);
    }
    
    @Transactional
    public void modificarFamilia(String id, String nombre, int edadMin, int edadMax, int numHijos, String email){
         
        Optional<Familia> respuesta = fr.findById(id);
        
        if (respuesta.isPresent()) {
            
            Familia f = respuesta.get();
            f.setNombre(nombre);
            f.setEdadMin(edadMin);
            f.setEdadMax(edadMax);
            f.setNumHijos(numHijos);
            f.setEmail(email);
        }
    }

}
