package vargas.stiven.iteriaAPI.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vargas.stiven.iteriaAPI.entity.EstadoEnum;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfiliadoDTO {

    private Long afi_id;
    private String afi_nombre;
    private String afi_apellidos;
    private Long tdc_id;
    private String afi_documento;
    private String afi_direccion;
    private String afi_telefono;
    private String afi_mail;

    @Enumerated(EnumType.ORDINAL)
    private EstadoEnum afi_estado;
}
