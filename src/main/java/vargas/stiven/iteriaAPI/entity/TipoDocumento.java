package vargas.stiven.iteriaAPI.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @Schema(example = "pk TipoDocumento table")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tdc_id;

    @Schema(example = "CC")
    @Size(min = 1, max = 45)
    @Column(unique=true, columnDefinition = "varchar(45)", nullable = false)
    private String tdc_nombre;

    @NotEmpty
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, columnDefinition = "integer default 0")
    @Schema(description = "ACTIVO=1 | INACTIVO=0", example = "ACTIVO")
    private EstadoEnum tdc_estado;

    public TipoDocumento(Long tdc_id) {
        this.tdc_id = tdc_id;
    }
}
