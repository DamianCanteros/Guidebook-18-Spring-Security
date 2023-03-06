
package Estancias.servicios;

import estancias.entidades.Reserva;
import estancias.repositorios.ReservaRepositorio;
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
public class ReservaServicio {
    
    @Autowired //Indica a Spring dónde debe ocurrir una inyección
    private ReservaRepositorio rr;
    
    @Transactional //se utiliza cada vez que vayamos a hacer una modificación en la BBDD
    public void altaReserva(String nombre, Date fechaDesde, Date fechaHasta) throws Exception{
             
        Reserva r = new Reserva();
        
        r.setHuesped(nombre);
        r.setFechaDesde(fechaDesde);
        r.setFechaHasta(fechaHasta);
        rr.save(r);

    }
    
    @Transactional
    public void eliminarReserva(String id){
             
        rr.deleteById(id);                

    }
    
    public List<Reserva> listarReservas() {
        List<Reserva> reservas = new ArrayList();
        reservas = rr.findAll();
        return reservas;
    }
    
    public Reserva getOne(String id){
        return rr.getOne(id);
    }

    @Transactional
    public void modificarReserva(String id, Date fechaDesde, Date fechaHasta){
         
        Optional<Reserva> respuesta = rr.findById(id);
        
        if (respuesta.isPresent()) {
            
            Reserva r = respuesta.get();
            
            r.setFechaDesde(fechaDesde);
            r.setFechaHasta(fechaHasta);
        }
    }
}
