package vargas.stiven.iteriaAPI.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "afiliado")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Afiliado {

    @Id
    @Schema(example = "pk Afiliado table")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long afi_id;

    @Schema(example = "Afiliado")
    @Size(min = 1, max = 45)
    private String afi_nombre;

    @Schema(example = "de prueba")
    @Size(min = 1, max = 45)
    private String afi_apellidos;

    @ManyToOne(targetEntity = TipoDocumento.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "tdc_id")
    private TipoDocumento tdc_id;

    @Schema(example = "123456")
    @Size(min = 1, max = 10)
    private String afi_documento;

    @Schema(example = "calle 1 # 4-56")
    @Size(min = 1, max = 100)
    private String afi_direccion;

    @Schema(example = "3102545696")
    @Size(min = 1, max = 10)
    private String afi_telefono;

    @Schema(example = "prueba@mail.com")
    @Size(min = 1, max = 100)
    private String afi_mail;

    @Enumerated(EnumType.ORDINAL)
    @Schema(description = "ACTIVO=1 | INACTIVO=0", example = "ACTIVO")
    private EstadoEnum afi_estado;

    public Afiliado(Long afi_id) {
        this.afi_id = afi_id;
    }
}
