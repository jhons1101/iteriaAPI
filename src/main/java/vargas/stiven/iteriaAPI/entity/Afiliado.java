package vargas.stiven.iteriaAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "afiliado")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Afiliado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long afi_id;

    private String afi_nombre;
    private String afi_apellidos;

    @ManyToOne
    @JoinColumn(name = "tdc_id")
    private TipoDocumento tdc_id;

    private String afi_documento;
    private String afi_direccion;
    private String afi_telefono;
    private String afi_mail;

    @Enumerated(EnumType.ORDINAL)
    private EstadoEnum afi_estado;
}
