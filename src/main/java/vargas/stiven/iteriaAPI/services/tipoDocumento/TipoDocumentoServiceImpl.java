package vargas.stiven.iteriaAPI.services.tipoDocumento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;
import vargas.stiven.iteriaAPI.repository.TipoDocumentoRepository;

import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService{

    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    public List<TipoDocumento> findAll() {
        return tipoDocumentoRepository.findAll();
    }

    @Override
    public TipoDocumento save(TipoDocumento tipoDoc) {
        return tipoDocumentoRepository.save(tipoDoc);
    }
}
