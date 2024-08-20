package vargas.stiven.iteriaAPI.services.contrato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vargas.stiven.iteriaAPI.dto.ContratoDTO;
import vargas.stiven.iteriaAPI.entity.Afiliado;
import vargas.stiven.iteriaAPI.entity.Contrato;
import vargas.stiven.iteriaAPI.entity.EstadoEnum;
import vargas.stiven.iteriaAPI.entity.Plan;
import vargas.stiven.iteriaAPI.mappers.ContratoMapper;
import vargas.stiven.iteriaAPI.repository.AfiliadoRepository;
import vargas.stiven.iteriaAPI.repository.ContratoRepository;
import vargas.stiven.iteriaAPI.repository.PlanRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContratoServiceImpl implements ContratoService{

    @Autowired
    ContratoRepository contratoRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    AfiliadoRepository afiliadoRepository;

    @Autowired
    ContratoMapper contratoMapper;

    @Override
    public List<ContratoDTO> findAll() {
        return contratoRepository.findAll().stream()
                .map(con -> contratoMapper.mapToContratoDTO(con))
                .collect(Collectors.toList());
    }

    @Override
    public ContratoDTO save(ContratoDTO contratoDto) throws IllegalArgumentException {
        try {
            validaContrato(contratoDto, true, null);
            Contrato contratoEntity = contratoMapper.mapToContrato(contratoDto);
            Contrato contratoDB = contratoRepository.save(contratoEntity);
            return contratoMapper.mapToContratoDTO(contratoDB);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public ContratoDTO update(Long id, ContratoDTO contratoDto) throws IllegalArgumentException {

        try {

            validaContrato(contratoDto, false, id);

            Optional<Afiliado> affiliatedDB = afiliadoRepository.findById(contratoDto.getAfi_id());
            Optional<Plan> planDB = planRepository.findById(contratoDto.getPln_id());

            Optional<Contrato> contratoDB = contratoRepository.findById(id);
            if (contratoDB.isEmpty()) {
                throw new IllegalArgumentException("El contrato con id " + id + "no existe");
            }

            if (contratoDto.getAfi_id() != null) {
                affiliatedDB.ifPresent(afiliado -> contratoDB.get().setAfi_id(afiliado));
            }

            if (contratoDto.getPln_id() != null) {
                planDB.ifPresent(plan -> contratoDB.get().setPln_id(plan));
            }
            if (contratoDto.getCto_cantidad_usuarios() != null) {
                contratoDB.get().setCto_cantidad_usuarios(contratoDto.getCto_cantidad_usuarios());
            }

            if (contratoDto.getCto_fecha_inicio() != null && !contratoDto.getCto_fecha_inicio().isEmpty()) {
                contratoDB.get().setCto_fecha_inicio(contratoDto.getCto_fecha_inicio());
            }

            if (contratoDto.getCto_fecha_retiro() != null && !contratoDto.getCto_fecha_retiro().isEmpty()) {
                contratoDB.get().setCto_fecha_retiro(contratoDto.getCto_fecha_retiro());
            }
            if (contratoDto.getCto_fecha_registro() != null && !contratoDto.getCto_fecha_registro().isEmpty()) {
                contratoDB.get().setCto_fecha_registro(contratoDto.getCto_fecha_registro());
            }
            if (contratoDto.getCto_eps() != null && !contratoDto.getCto_eps().isEmpty()) {
                contratoDB.get().setCto_eps(contratoDto.getCto_eps());
            }

            Contrato contratoSaved = contratoRepository.save(contratoDB.get());
            return contratoMapper.mapToContratoDTO(contratoSaved);

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public List<ContratoDTO> findByAfiId(Long afiId) {
        return contratoRepository.findByAfiId(afiId).stream()
                .map(contrato -> contratoMapper.mapToContratoDTO(contrato))
                .collect(Collectors.toList());
    }

    @Override
    public void updateWithdrawalDateByAfiId(Long afiId) {
        contratoRepository.updateWithdrawalDateByAfiId(afiId);
    }

    public Optional<ContratoDTO> findByPlnIDAndByAfiId(ContratoDTO contratoDto) {
        Optional<Contrato> contratoDB = contratoRepository.findByPlnIDAndByAfiId(contratoDto.getPln_id(), contratoDto.getAfi_id());
        return contratoDB.map(contrato -> contratoMapper.mapToContratoDTO(contrato));
    }

    public Optional<ContratoDTO> findByPlnIDAndByAfiIdAndCtoId(ContratoDTO contratoDto, Long id) {
        Optional<Contrato> contratoDB = contratoRepository.findByPlnIDAndByAfiIdAndCtoId(contratoDto.getPln_id(), contratoDto.getAfi_id(), id);
        return contratoDB.map(contrato -> contratoMapper.mapToContratoDTO(contrato));
    }

    private void validaContrato(ContratoDTO contratoDto, Boolean flgCreate, Long id) throws IllegalArgumentException {

        Optional<Plan> planDB = planRepository.findById(contratoDto.getPln_id());
        Optional<Afiliado> affiliatedDB = afiliadoRepository.findById(contratoDto.getAfi_id());

        if (planDB.isEmpty()) {
            throw new IllegalArgumentException("No se puede registrar el contrato, el plan seleccionado no existe");
        } else {

            if (!planDB.get().getPln_estado().equals(EstadoEnum.ACTIVO)) {
                throw new IllegalArgumentException("No se puede registrar el contrato, solo se pueden usar planes en estado activo");
            } else {

                LocalDate now = LocalDate.now();
                LocalDate plan = LocalDate.parse(planDB.get().getPln_fecha_fin());

                if (now.isAfter(plan) || now.isEqual(plan)) {
                    throw new IllegalArgumentException("No se puede registrar un contrato si el plan seleccionado tiene fecha fin menor o igual al día de registro del contrato " + planDB.get().getPln_fecha_fin());
                }

                Optional<ContratoDTO> afiContratoPlan;

                if (Boolean.TRUE.equals(flgCreate)) {
                    afiContratoPlan = this.findByPlnIDAndByAfiId(contratoDto);
                } else {
                    afiContratoPlan = this.findByPlnIDAndByAfiIdAndCtoId(contratoDto, id);
                }
                if (afiContratoPlan.isPresent()) {
                    throw new IllegalArgumentException("No se puede registrar el contrato, un afiliado no puede contratar el mismo plan si este ya esta asociado y activo");
                }
            }
        }

        if (affiliatedDB.isEmpty()) {
            throw new IllegalArgumentException("El afiliado con id " + contratoDto.getAfi_id() + " no existe");
        }
    }
}
