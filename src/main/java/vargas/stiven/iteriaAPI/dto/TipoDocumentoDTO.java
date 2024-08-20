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
public class TipoDocumentoDTO {

    @Schema(example = "pk TipoDocumento table")
    private Long tdc_id;

    @Schema(example = "CC")
    @Size(min = 1, max = 45)
    private String tdc_nombre;

    @Enumerated(EnumType.ORDINAL)
    @Schema(description = "ACTIVO=1 | INACTIVO=0", example = "ACTIVO")
    private EstadoEnum tdc_estado;
}
