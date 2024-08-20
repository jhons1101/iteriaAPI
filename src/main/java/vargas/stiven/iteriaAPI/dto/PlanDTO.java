package vargas.stiven.iteriaAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vargas.stiven.iteriaAPI.entity.EstadoEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {

    @Schema(example = "pk Plan table")
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
}
