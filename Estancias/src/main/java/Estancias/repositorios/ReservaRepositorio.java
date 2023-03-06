
package estancias.repositorios;

import estancias.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Damian
 */
@Repository
public interface ReservaRepositorio extends JpaRepository <Reserva, String>{
    
}
