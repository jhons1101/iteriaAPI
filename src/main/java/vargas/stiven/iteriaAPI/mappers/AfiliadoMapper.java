package vargas.stiven.iteriaAPI.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vargas.stiven.iteriaAPI.dto.AfiliadoDTO;
import vargas.stiven.iteriaAPI.entity.Afiliado;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;
import vargas.stiven.iteriaAPI.repository.TipoDocumentoRepository;

import java.util.Optional;
@Component
public class AfiliadoMapper {

    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;
    
    // Convert Afiliado JPA Entity to AfiliadoDTO
    public AfiliadoDTO mapToAfiliadoDTO(Afiliado afiliado){
        return new AfiliadoDTO(
                afiliado.getAfi_id(),
                afiliado.getAfi_nombre(),
                afiliado.getAfi_apellidos(),
                afiliado.getTdc_id().getTdc_id(),
                afiliado.getAfi_documento(),
                afiliado.getAfi_direccion(),
                afiliado.getAfi_telefono(),
                afiliado.getAfi_mail(),
                afiliado.getAfi_estado()
        );
    }

    // Convert AfiliadoDTO to Afiliado JPA Entity
    public Afiliado mapToAfiliado(AfiliadoDTO afiliadoDto){

        Optional<TipoDocumento> tipoDoc = tipoDocumentoRepository.findById(afiliadoDto.getTdc_id());
        TipoDocumento tipoDocumento = new TipoDocumento(afiliadoDto.getTdc_id());

        if (tipoDoc.isPresent()) {
            tipoDocumento = tipoDoc.get();
        }

        return new Afiliado(
                afiliadoDto.getAfi_id(),
                afiliadoDto.getAfi_nombre(),
                afiliadoDto.getAfi_apellidos(),
                tipoDocumento,
                afiliadoDto.getAfi_documento(),
                afiliadoDto.getAfi_direccion(),
                afiliadoDto.getAfi_telefono(),
                afiliadoDto.getAfi_mail(),
                afiliadoDto.getAfi_estado()
        );
    }
}
