
package estancias.repositorios;

import estancias.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Damian
 */
   
@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario, String>{

//realiza una búsqueda a través de los parámetros señalados
	@Query("SELECT u FROM Usuario u WHERE u.alias = :alias")
	public Usuario buscarUsuario(@Param("alias") String alias);
}
