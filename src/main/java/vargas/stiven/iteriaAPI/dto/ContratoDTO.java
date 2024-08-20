package vargas.stiven.iteriaAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratoDTO {

    @Schema(example = "pk Contrato table")
    private Long cto_id;

    @Schema(example = "fk Afiliado table")
    private Long afi_id;

    @Schema(example = "fk Plan table")
    private Long pln_id;

    @Min(0)
    @Max(100)
    private Integer cto_cantidad_usuarios;

    @Schema(example = "2024-08-01")
    @Size(min = 10, max = 10)
    private String cto_fecha_inicio;

    @Schema(example = "2024-08-20")
    @Size(min = 10, max = 10)
    private String cto_fecha_retiro;

    @Schema(example = "2024-08-20")
    @Size(min = 10, max = 10)
    private String cto_fecha_registro;

    @Size(min = 0, max = 45)
    @Schema(example = "EPS x")
    private String cto_eps;
}
