
package estancias.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Damian
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Familia {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String id;
    @Column
    private String nombre;
    @Column
    private int edadMin;
    @Column
    private int edadMax;
    @Column
    private int numHijos;
    @Column
    private String email;
}
