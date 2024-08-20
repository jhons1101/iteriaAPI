package vargas.stiven.iteriaAPI.services.afiliado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vargas.stiven.iteriaAPI.dto.AfiliadoDTO;
import vargas.stiven.iteriaAPI.entity.Afiliado;
import vargas.stiven.iteriaAPI.entity.EstadoEnum;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;
import vargas.stiven.iteriaAPI.mappers.AfiliadoMapper;
import vargas.stiven.iteriaAPI.repository.AfiliadoRepository;
import vargas.stiven.iteriaAPI.repository.ContratoRepository;
import vargas.stiven.iteriaAPI.repository.TipoDocumentoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AfiladoServiceImpl implements AfiliadoService {

    @Autowired
    AfiliadoRepository afiliadoRepository;

    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    ContratoRepository contratoRepository;

    @Autowired
    AfiliadoMapper afiliadoMapper;

    @Override
    public List<AfiliadoDTO> findAll() {
        List<Afiliado> afiliadoList = afiliadoRepository.findAll();
        if (afiliadoList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return afiliadoList.stream()
                    .map(afi -> afiliadoMapper.mapToAfiliadoDTO(afi))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public AfiliadoDTO save (AfiliadoDTO affiliated) throws IllegalArgumentException {
        try {
            this.validaAfiliado(affiliated, true, null);

            // Convert AfiliadoDto to Afiliado JPA Entity
            Afiliado affiliatedEntity = afiliadoMapper.mapToAfiliado(affiliated);

            Afiliado savedAfiliado = afiliadoRepository.save(affiliatedEntity);

            // Convert Afiliado JPA entity to AfiliadoDto
            return afiliadoMapper.mapToAfiliadoDTO(savedAfiliado);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Optional<AfiliadoDTO> findByTpoDocAndDoc(AfiliadoDTO affiliated) {
        Optional<Afiliado> affiliatedEntity = afiliadoRepository.findByTpoDocAndDoc(affiliated.getTdc_id(), affiliated.getAfi_documento());
        return affiliatedEntity.map(afiliado -> afiliadoMapper.mapToAfiliadoDTO(afiliado));
    }

    public Optional<AfiliadoDTO> findByTpoDocAndDocWhenIsNotSame(AfiliadoDTO afiliadoDto, Long id) {
        Optional<Afiliado> afiliadoEntity =  afiliadoRepository.findByTpoDocAndDocWhenIsNotSame(afiliadoDto.getTdc_id(), afiliadoDto.getAfi_documento(), id);
        return afiliadoEntity.map(afiliado -> afiliadoMapper.mapToAfiliadoDTO(afiliado));
    }

    @Override
    public AfiliadoDTO update(Long id, AfiliadoDTO affiliated) throws IllegalArgumentException {

        try {
            Optional<Afiliado> afiliadoDB = afiliadoRepository.findById(id);
            if (afiliadoDB.isEmpty()) {
                throw new IllegalArgumentException("El afiliado con id " + id + "no existe");
            }

            if (affiliated.getAfi_apellidos() != null && !affiliated.getAfi_apellidos().isEmpty()) {
                afiliadoDB.get().setAfi_apellidos(affiliated.getAfi_apellidos());
            }

            if (affiliated.getAfi_nombre() != null && !affiliated.getAfi_nombre().isEmpty()) {
                afiliadoDB.get().setAfi_nombre(affiliated.getAfi_nombre());
            }

            if (affiliated.getAfi_direccion() != null && !affiliated.getAfi_direccion().isEmpty()) {
                afiliadoDB.get().setAfi_direccion(affiliated.getAfi_direccion());
            }

            if (affiliated.getAfi_documento() != null && !affiliated.getAfi_documento().isEmpty()) {
                afiliadoDB.get().setAfi_documento(affiliated.getAfi_documento());
            }

            if (affiliated.getAfi_mail() != null && !affiliated.getAfi_mail().isEmpty()) {
                afiliadoDB.get().setAfi_mail(affiliated.getAfi_mail());
            }

            if (affiliated.getAfi_telefono() != null && !affiliated.getAfi_telefono().isEmpty()) {
                afiliadoDB.get().setAfi_telefono(affiliated.getAfi_telefono());
            }

            if (affiliated.getAfi_estado() != null) {
                afiliadoDB.get().setAfi_estado(affiliated.getAfi_estado());
            }

            validaAfiliado(affiliated, false, id);

            if (affiliated.getTdc_id() != null) {
                Optional<TipoDocumento> tipoDoc = tipoDocumentoRepository.findById(affiliated.getTdc_id());
                tipoDoc.ifPresent(tipoDocumento -> afiliadoDB.get().setTdc_id(tipoDocumento));
            }

            afiliadoRepository.save(afiliadoDB.get());

            if (affiliated.getAfi_estado() != null && affiliated.getAfi_estado().equals(EstadoEnum.INACTIVO)) {
                contratoRepository.updateWithdrawalDateByAfiId(afiliadoDB.get().getAfi_id());
            }

            return afiliadoMapper.mapToAfiliadoDTO(afiliadoDB.get());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void validaAfiliado(AfiliadoDTO affiliated, Boolean flgCreate, Long id) throws IllegalArgumentException {

        Optional<TipoDocumento> tipoDoc = tipoDocumentoRepository.findById(affiliated.getTdc_id());

        if (tipoDoc.isEmpty()) {
            throw new IllegalArgumentException("No se puede insertar el Afiliado, el estado del tipo de documento no existe");
        } else if (!tipoDoc.get().getTdc_estado().equals(EstadoEnum.ACTIVO)) {
            throw new IllegalArgumentException("No se puede insertar el Afiliado, el estado del tipo de documento está inactivo");
        }

        Optional<AfiliadoDTO> affiliatedDB;

        if (Boolean.TRUE.equals(flgCreate)) {
            affiliatedDB = this.findByTpoDocAndDoc(affiliated);
        } else {
            affiliatedDB = this.findByTpoDocAndDocWhenIsNotSame(affiliated, id);
        }
        if (affiliatedDB.isPresent()) {
            throw new IllegalArgumentException("Ya existe un afiliado con el mismo tipo y numero de documento");
        }
    }
}
