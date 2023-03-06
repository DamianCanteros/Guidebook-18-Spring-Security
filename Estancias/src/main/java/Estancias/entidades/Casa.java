
package estancias.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table
@Getter
@Setter
@NoArgsConstructor
public class Casa {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String id;
    @Column
    private String calle;
    @Column
    private int numero;
    @Column
    private String codPostal;
    @Column
    private String ciudad;
    @Column
    private String pais;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDesde;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaHasta;
    @Column
    private int minDias;
    @Column
    private int maxDias;
    @Column
    private double precio;
    @Column
    private String tipoVivienda;
}
