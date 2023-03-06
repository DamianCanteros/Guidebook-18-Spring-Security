
package com.egg.news.repositorio;

import com.egg.news.entidad.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Damian
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario,String> {
    
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    public Usuario buscarNombre(@Param("nombreUsuario")String nombreUsuario);
    
    @Query("SELECT u FROM Usuario u WHERE u.rol LIKE 1")
    public List<Usuario> buscarPeriodistas();
 
}
