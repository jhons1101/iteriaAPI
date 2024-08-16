package vargas.stiven.iteriaAPI.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vargas.stiven.iteriaAPI.entity.EstadoEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoDTO {

    private Long tdc_id;
    private String tdc_nombre;

    @Enumerated(EnumType.ORDINAL)
    private EstadoEnum tdc_estado;
}
