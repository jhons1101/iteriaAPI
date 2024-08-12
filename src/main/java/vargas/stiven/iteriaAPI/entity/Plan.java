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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pln_id;

    private String pln_nombre;

    @Schema(example = "2024-08-01")
    @Size(min = 10, max = 10)
    private String pln_fecha_inicio;

    @Schema(example = "2024-08-01")
    @Size(min = 10, max = 10)
    private String pln_fecha_fin;

    @Enumerated(EnumType.ORDINAL)
    private EstadoEnum pln_estado;
}
