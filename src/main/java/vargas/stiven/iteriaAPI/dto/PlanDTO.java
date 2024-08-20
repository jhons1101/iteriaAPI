package vargas.stiven.iteriaAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {

    private Long pln_id;
    private String pln_nombre;
    private String pln_fecha_inicio;
    private String pln_fecha_fin;
    private Long pln_estado;
}
