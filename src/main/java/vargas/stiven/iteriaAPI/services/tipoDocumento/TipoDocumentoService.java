package vargas.stiven.iteriaAPI.services.tipoDocumento;

import vargas.stiven.iteriaAPI.entity.TipoDocumento;

import java.util.List;

public interface TipoDocumentoService {

    List<TipoDocumento> findAll();

    TipoDocumento save(TipoDocumento tipoDoc);
}
