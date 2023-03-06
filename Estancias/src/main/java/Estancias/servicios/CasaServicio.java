
package Estancias.servicios;

import estancias.entidades.Casa;
import estancias.repositorios.CasaRepositorio;
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

@Service//recuperar datos relacionados con el usuario
public class CasaServicio {
    
    @Autowired //Indica a Spring dónde debe ocurrir una inyección
    private CasaRepositorio cr;
    
    @Transactional //se utiliza cada vez que vayamos a hacer una modificación en la BBDD
    public void altaCasa(String calle, int numero, String codPostal, String ciudad, String pais, Date fechaDesde,
                Date fechaHasta, int minDias, int maxDias, int precio, String tipoVivienda) throws Exception{
             
        Casa c = new Casa();

        c.setCalle(calle);
        c.setNumero(numero);
        c.setCodPostal(codPostal);
        c.setCiudad(ciudad);
        c.setPais(pais);
        c.setFechaDesde(fechaDesde);
        c.setFechaHasta(fechaHasta);
        c.setMinDias(minDias);
        c.setMaxDias(maxDias);
        c.setPrecio(precio);
        c.setTipoVivienda(tipoVivienda);
        cr.save(c);

    }
    
    @Transactional
    public void eliminarCasa(String id){
             
        cr.deleteById(id);                

    }
    
    public List<Casa> listarCasas() {
        List<Casa> casas = new ArrayList();
        casas = cr.findAll();
        return casas;
    }
    
    public Casa getOne(String id){
        return cr.getOne(id);
    }

    @Transactional
    public void modificarCasa(String id, String calle, int numero, String codPostal, String ciudad, String pais, Date fechaDesde,
                Date fechaHasta, int minDias, int maxDias, int precio, String tipoVivienda){
         
        Optional<Casa> respuesta = cr.findById(id);
        
        if (respuesta.isPresent()) {
            
            Casa c = respuesta.get();
            
            c.setCalle(calle);
            c.setNumero(numero);
            c.setCodPostal(codPostal);
            c.setCiudad(ciudad);
            c.setPais(pais);
            c.setFechaDesde(fechaDesde);
            c.setFechaHasta(fechaHasta);
            c.setMinDias(minDias);
            c.setMaxDias(maxDias);
            c.setPrecio(precio);
            c.setTipoVivienda(tipoVivienda);
        }
    }
}
