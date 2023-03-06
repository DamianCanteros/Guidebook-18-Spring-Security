
package estancias.repositorios;

import estancias.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Damian
 */
@Repository
public interface ClienteRepositorio extends JpaRepository <Cliente, String>{

//realiza una búsqueda a través de los parámetros señalados
	@Query("SELECT f FROM Familia f WHERE f.email = :email")
	public Cliente buscarEmail(@Param("email") String email);
}
