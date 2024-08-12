package vargas.stiven.iteriaAPI.services.afiliado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vargas.stiven.iteriaAPI.entity.Afiliado;
import vargas.stiven.iteriaAPI.entity.Contrato;
import vargas.stiven.iteriaAPI.entity.EstadoEnum;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;
import vargas.stiven.iteriaAPI.repository.AfiliadoRepository;
import vargas.stiven.iteriaAPI.repository.ContratoRepository;
import vargas.stiven.iteriaAPI.repository.TipoDocumentoRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AfiladoServiceImpl implements AfiliadoService {

    @Autowired
    AfiliadoRepository afiliadoRepository;

    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    ContratoRepository contratoRepository;

    @Override
    public List<Afiliado> findAll() {
        return afiliadoRepository.findAll();
    }

    @Override
    public Afiliado save (Afiliado afiliado) throws Exception {
        this.validaAfiliado(afiliado, true, null);
        return afiliadoRepository.save(afiliado);
    }

    public Optional<Afiliado> findByTpoDocAndDoc(Afiliado afiliado) {
        return afiliadoRepository.findByTpoDocAndDoc(afiliado.getTdc_id(), afiliado.getAfi_documento());
    }

    public Optional<Afiliado> findByTpoDocAndDocWhenIsNotSame(Afiliado afiliado, Long id) {
        return afiliadoRepository.findByTpoDocAndDocWhenIsNotSame(afiliado.getTdc_id().getTdc_id(), afiliado.getAfi_documento(), id);
    }

    @Override
    public Afiliado update(Long id, Afiliado afiliado) throws Exception {

        Optional<Afiliado> afiliadoDB = afiliadoRepository.findById(id);
        if (afiliadoDB.isEmpty()) {
            throw new Exception("El afiliado con id " + id + "no existe");
        }

        if (afiliado.getTdc_id() != null) {
            afiliadoDB.get().setTdc_id(afiliado.getTdc_id());
        }

        if (afiliado.getAfi_apellidos() != null && !afiliado.getAfi_apellidos().isEmpty()) {
            afiliadoDB.get().setAfi_apellidos(afiliado.getAfi_apellidos());
        }

        if (afiliado.getAfi_nombre() != null && !afiliado.getAfi_nombre().isEmpty()) {
            afiliadoDB.get().setAfi_nombre(afiliado.getAfi_nombre());
        }

        if (afiliado.getAfi_direccion() != null && !afiliado.getAfi_direccion().isEmpty()) {
            afiliadoDB.get().setAfi_direccion(afiliado.getAfi_direccion());
        }

        if (afiliado.getAfi_documento() != null && !afiliado.getAfi_documento().isEmpty()) {
            afiliadoDB.get().setAfi_documento(afiliado.getAfi_documento());
        }

        if (afiliado.getAfi_mail() != null && !afiliado.getAfi_mail().isEmpty()) {
            afiliadoDB.get().setAfi_mail(afiliado.getAfi_mail());
        }

        if (afiliado.getAfi_telefono() != null && !afiliado.getAfi_telefono().isEmpty()) {
            afiliadoDB.get().setAfi_telefono(afiliado.getAfi_telefono());
        }

        if (afiliado.getAfi_estado() != null) {
            afiliadoDB.get().setAfi_estado(afiliado.getAfi_estado());
        }

        validaAfiliado(afiliadoDB.get(), false, id);
        afiliadoRepository.save(afiliadoDB.get());

        if (afiliado.getAfi_estado() != null && afiliado.getAfi_estado().equals(EstadoEnum.INACTIVO)) {
            contratoRepository.updateWithdrawalDateByAfiId(afiliadoDB.get().getAfi_id());
        }
        return afiliadoRepository.findById(id).get();

    }

    private void validaAfiliado(Afiliado afiliado, Boolean flg_create, Long id) throws Exception {

        Optional<TipoDocumento> tipoDoc = tipoDocumentoRepository.findById(afiliado.getTdc_id().getTdc_id());

        if (tipoDoc.isEmpty()) {
            throw new Exception("No se puede insertar el Afiliado, el estado del tipo de documento no existe");
        } else if (!tipoDoc.get().getTdc_estado().equals(EstadoEnum.ACTIVO)) {
            throw new Exception("No se puede insertar el Afiliado, el estado del tipo de documento está inactivo");
        }

        Optional<Afiliado> Afiliado = Optional.empty();

        if (flg_create) {
            Afiliado = this.findByTpoDocAndDoc(afiliado);
        } else {
            Afiliado = this.findByTpoDocAndDocWhenIsNotSame(afiliado, id);
        }
        if (!Afiliado.isEmpty()) {
            throw new Exception("Ya existe un afiliado con el mismo tipo y numero de documento");
        }
    }
}
