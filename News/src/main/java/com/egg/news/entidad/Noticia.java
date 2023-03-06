
package com.egg.news.entidad;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Damian
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor 
public class Noticia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    
    @Column
    private String titulo;
    
    @Column
    private String cuerpo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column
    private Date alta;
    
    @ManyToOne
    @JoinColumn
    private Periodista creador; 

}
