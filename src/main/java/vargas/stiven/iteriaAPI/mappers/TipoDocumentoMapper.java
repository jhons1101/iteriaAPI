package vargas.stiven.iteriaAPI.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vargas.stiven.iteriaAPI.dto.TipoDocumentoDTO;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;
import vargas.stiven.iteriaAPI.repository.TipoDocumentoRepository;

import java.util.Optional;

@Component
public class TipoDocumentoMapper {
    
    // Convert TipoDocumento JPA Entity to TipoDocumentoDTO
    public TipoDocumentoDTO mapToTipoDocumentoDTO(TipoDocumento tipoDocumento){
        return new TipoDocumentoDTO(
                tipoDocumento.getTdc_id(),
                tipoDocumento.getTdc_nombre(),
                tipoDocumento.getTdc_estado()
        );
    }

    // Convert TipoDocumentoDTO to TipoDocumento JPA Entity
    public TipoDocumento mapToTipoDocumento(TipoDocumentoDTO tipoDocumentoDto){

        return new TipoDocumento(
                tipoDocumentoDto.getTdc_id(),
                tipoDocumentoDto.getTdc_nombre(),
                tipoDocumentoDto.getTdc_estado()
        );
    }
}
