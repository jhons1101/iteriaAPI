package vargas.stiven.iteriaAPI.services.tipoDocumento;

import vargas.stiven.iteriaAPI.dto.TipoDocumentoDTO;

import java.util.List;

public interface TipoDocumentoService {

    List<TipoDocumentoDTO> findAll();

    TipoDocumentoDTO save(TipoDocumentoDTO tipDoc);
}
