
package com.egg.news.entidad;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Damian
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="rol", 
  discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
@Table
@Getter
@Setter
@NoArgsConstructor 
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String id;
    
    @Column
    private String nombreUsuario;
    
    @Column
    private String password;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column
    private Date alta;
    
    @Enumerated
    @Column(name = "rol", insertable = false, updatable = false)
    private Rol rol;
    
    @Column
    private Boolean activo=true;

}
