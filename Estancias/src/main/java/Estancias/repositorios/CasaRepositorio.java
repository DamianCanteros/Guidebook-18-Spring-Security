
package estancias.repositorios;


import estancias.entidades.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Damian
 */
@Repository
public interface CasaRepositorio extends JpaRepository <Casa, String>{
    
}
