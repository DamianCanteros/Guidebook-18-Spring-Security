
package com.egg.news.entidad;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Damian
 */
@Entity
@DiscriminatorValue("1")
@Getter
@Setter
@NoArgsConstructor 
public class Periodista extends Usuario{
    
    @OneToMany(mappedBy="creador")
    private List<Noticia> misNoticias;
    
    @Column 
    private Integer sueldoMensual;

}
