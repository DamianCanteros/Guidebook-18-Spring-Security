
package estancias.repositorios;

import estancias.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Damian
 */
@Repository
public interface ComentarioRepositorio extends JpaRepository <Comentario, String>{
    
}
