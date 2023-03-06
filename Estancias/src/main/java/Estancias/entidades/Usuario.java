
package estancias.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Damian
 */
@Entity
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
//    @NotBlank(message = "debe indicar el alias")
    private String alias;
    @Column
//    @Email
//    @NotBlank
    private String email;
    @Column
//    @Size(min = 6, message = "la clave debe contener al menos 6 digitos")
    private String clave;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAlta;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaBaja;
    @Enumerated
    @Column
    private Rol rol;
            
}
