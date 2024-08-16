package vargas.stiven.iteriaAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "tipo_documento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tdc_id;

    @Column(unique=true, columnDefinition = "varchar(45)", nullable = false)
    private String tdc_nombre;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, columnDefinition = "integer default 0")
    @NotEmpty
    private EstadoEnum tdc_estado;

    public TipoDocumento(Long tdc_id) {
        this.tdc_id = tdc_id;
    }
}
