package vargas.stiven.iteriaAPI.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="plan")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plan {

    @Id
    @Schema(example = "pk Plan table")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pln_id;

    @Size(min = 1, max = 45)
    private String pln_nombre;

    @Schema(example = "2024-08-01")
    @Size(min = 10, max = 10)
    private String pln_fecha_inicio;

    @Schema(example = "2024-08-01")
    @Size(min = 10, max = 10)
    private String pln_fecha_fin;

    @Enumerated(EnumType.ORDINAL)
    @Schema(description = "ACTIVO=1 | INACTIVO=0", example = "ACTIVO")
    private EstadoEnum pln_estado;

    public Plan(Long pln_id) {
        this.pln_id = pln_id;
    }
}
