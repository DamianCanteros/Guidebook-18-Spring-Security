
package com.egg.news.entidad;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Damian
 */
@Entity
@DiscriminatorValue("2")
public class Administrador extends Usuario{
    
}
