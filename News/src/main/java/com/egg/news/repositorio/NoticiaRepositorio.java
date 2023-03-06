
package com.egg.news.repositorio;

import com.egg.news.entidad.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Damian
 */
@Repository
public interface NoticiaRepositorio extends JpaRepository <Noticia,Integer> {
    
}
