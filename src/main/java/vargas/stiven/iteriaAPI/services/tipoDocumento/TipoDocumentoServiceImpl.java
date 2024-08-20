package vargas.stiven.iteriaAPI.services.tipoDocumento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vargas.stiven.iteriaAPI.dto.TipoDocumentoDTO;
import vargas.stiven.iteriaAPI.entity.EstadoEnum;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;
import vargas.stiven.iteriaAPI.mappers.TipoDocumentoMapper;
import vargas.stiven.iteriaAPI.repository.TipoDocumentoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService{

    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    TipoDocumentoMapper tipoDocumentoMapper;

    @Override
    public List<TipoDocumentoDTO> findAll() {
        List<TipoDocumento> tpoDocumentoList = tipoDocumentoRepository.findAll();
        if (tpoDocumentoList.isEmpty()){
            return new ArrayList<>();
        } else {
            return tpoDocumentoList.stream()
                    .map(tpo -> tipoDocumentoMapper.mapToTipoDocumentoDTO(tpo))
                    .collect(Collectors.toList());
        }
    }

    public Optional<TipoDocumentoDTO> findByName(String tpoDoc) {
        Optional<TipoDocumento> tpoDocEntity = tipoDocumentoRepository.findByName(tpoDoc);
        return tpoDocEntity.map(doc -> tipoDocumentoMapper.mapToTipoDocumentoDTO(doc));
    }

    public Optional<TipoDocumentoDTO> findByNameWhenIsNotSame(String tpoDoc, Long id) {
        Optional<TipoDocumento> tpoDocEntity =  tipoDocumentoRepository.findByNameWhenIsNotSame(tpoDoc, id);
        return tpoDocEntity.map(doc -> tipoDocumentoMapper.mapToTipoDocumentoDTO(doc));
    }

    @Override
    public TipoDocumentoDTO save(TipoDocumentoDTO tipDoc) {
        try {
            this.tipoDocumentoValidate(tipDoc, true, null);
            TipoDocumento tpoDocumento = tipoDocumentoMapper.mapToTipoDocumento(tipDoc);
            TipoDocumento tpoDocumentoEntity = tipoDocumentoRepository.save(tpoDocumento);
            return tipoDocumentoMapper.mapToTipoDocumentoDTO(tpoDocumentoEntity);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void tipoDocumentoValidate(TipoDocumentoDTO tpoDoc, Boolean flgCreate, Long id) {

        if (tpoDoc.getTdc_estado() == null) {
            tpoDoc.setTdc_estado(EstadoEnum.INACTIVO);
        }

        if (tpoDoc.getTdc_nombre().equals(null) || tpoDoc.getTdc_nombre() == "" || tpoDoc.getTdc_nombre().isEmpty()) {
            throw new IllegalArgumentException("El campo nombre es obligatorio");
        } else {

            Optional<TipoDocumentoDTO> doc;

            if (Boolean.TRUE.equals(flgCreate)) {
                doc = this.findByName(tpoDoc.getTdc_nombre());
            } else {
                doc = this.findByNameWhenIsNotSame(tpoDoc.getTdc_nombre(), id);
            }

            if (doc.isPresent()) {
                throw new IllegalArgumentException("Ya existe un registro con el mismo nombre de documento");
            }
        }
    }
}
