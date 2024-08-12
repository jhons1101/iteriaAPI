package vargas.stiven.iteriaAPI.services.contrato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vargas.stiven.iteriaAPI.entity.Afiliado;
import vargas.stiven.iteriaAPI.entity.Contrato;
import vargas.stiven.iteriaAPI.entity.EstadoEnum;
import vargas.stiven.iteriaAPI.entity.Plan;
import vargas.stiven.iteriaAPI.repository.ContratoRepository;
import vargas.stiven.iteriaAPI.repository.PlanRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContratoServiceImpl implements ContratoService{

    @Autowired
    ContratoRepository contratoRepository;

    @Autowired
    PlanRepository planRepository;

    @Override
    public List<Contrato> findAll() {
        return contratoRepository.findAll();
    }

    @Override
    public Contrato save(Contrato contrato) throws Exception {
        validaContrato(contrato, true, null);
        Contrato contratoDB = contratoRepository.save(contrato);
        System.out.println(contratoDB);
        return contratoRepository.findById(contratoDB.getCto_id()).get();
    }

    @Override
    public Contrato update(Long id, Contrato contrato) throws Exception {

        Optional<Contrato> contratoDB = contratoRepository.findById(id);
        if (contratoDB.isEmpty()) {
            throw new Exception("El contrato con id " + id + "no existe");
        }

        contratoDB.get().setCto_id(id);
        contratoDB.get().setAfi_id(contrato.getAfi_id());
        contratoDB.get().setPln_id(contrato.getPln_id());
        contratoDB.get().setCto_cantidad_usuarios(contrato.getCto_cantidad_usuarios());
        contratoDB.get().setCto_fecha_inicio(contrato.getCto_fecha_inicio());
        contratoDB.get().setCto_fecha_retiro(contrato.getCto_fecha_retiro());
        contratoDB.get().setCto_fecha_registro(contrato.getCto_fecha_registro());
        contratoDB.get().setCto_eps(contrato.getCto_eps());

        validaContrato(contratoDB.get(), false, id);
        contratoRepository.save(contratoDB.get());
        return contratoRepository.findById(id).get();

    }

    @Override
    public List<Contrato> findByAfiId(Long afiId) {
        return contratoRepository.findByAfiId(afiId);
    }

    @Override
    public void updateWithdrawalDateByAfiId(Long afiId) {
        contratoRepository.updateWithdrawalDateByAfiId(afiId);
    }

    public Optional<Contrato> findByPlnIDAndByAfiId(Contrato contrato) {
        return contratoRepository.findByPlnIDAndByAfiId(contrato.getPln_id(), contrato.getAfi_id());
    }

    public Optional<Contrato> findByPlnIDAndByAfiIdAndCtoId(Contrato contrato, Long id) {
        return contratoRepository.findByPlnIDAndByAfiIdAndCtoId(contrato.getPln_id().getPln_id(), contrato.getAfi_id().getAfi_id(), id);
    }

    private void validaContrato(Contrato contrato, Boolean flg_create, Long id) throws Exception {

        Optional<Plan> planDB = planRepository.findById(contrato.getPln_id().getPln_id());

        if (planDB.isEmpty()) {
            throw new Exception("No se puede registrar el contrato, el plan seleccionado no existe");
        } else {

            if (!planDB.get().getPln_estado().equals(EstadoEnum.ACTIVO)) {
                throw new Exception("No se puede registrar el contrato, solo se pueden usar planes en estado activo");
            } else {

                LocalDate fnow = LocalDate.now();
                LocalDate fplan = LocalDate.parse(planDB.get().getPln_fecha_fin());

                if (fnow.isAfter(fplan) || fnow.isEqual(fplan)) {
                    throw new Exception("No se puede registrar un contrato si el plan seleccionado tiene fecha fin menor o igual al día de registro del contrato " + planDB.get().getPln_fecha_fin());
                }

                Optional<Contrato> afiContratoPlan = Optional.empty();

                if (flg_create) {
                    afiContratoPlan = this.findByPlnIDAndByAfiId(contrato);
                } else {
                    afiContratoPlan = this.findByPlnIDAndByAfiIdAndCtoId(contrato, id);
                }
                if (afiContratoPlan.isPresent()) {
                    throw new Exception("No se puede registrar el contrato, un afiliado no puede contratar el mismo plan si este ya esta asociado y activo");
                }
            }
        }
    }
}
