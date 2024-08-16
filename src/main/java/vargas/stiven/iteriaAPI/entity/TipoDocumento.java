package vargas.stiven.iteriaAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tipo_documento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tdc_id;

    private String tdc_nombre;

    @Enumerated(EnumType.ORDINAL)
    private EstadoEnum tdc_estado;

    public TipoDocumento(Long tdc_id) {
        this.tdc_id = tdc_id;
    }
}
