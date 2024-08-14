package vargas.stiven.iteriaAPI.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="contrato")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cto_id;

    @ManyToOne(targetEntity = Afiliado.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "afi_id")
    private Afiliado afi_id;

    @ManyToOne(targetEntity = Plan.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "pln_id")
    private Plan pln_id;

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

    private String cto_eps;
}
