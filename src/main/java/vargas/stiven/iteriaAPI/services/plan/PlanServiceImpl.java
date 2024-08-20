package vargas.stiven.iteriaAPI.services.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vargas.stiven.iteriaAPI.dto.PlanDTO;
import vargas.stiven.iteriaAPI.entity.EstadoEnum;
import vargas.stiven.iteriaAPI.entity.Plan;
import vargas.stiven.iteriaAPI.mappers.PlanMapper;
import vargas.stiven.iteriaAPI.repository.PlanRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    PlanRepository planRepository;

    @Autowired
    PlanMapper planMapper;

    @Override
    public List<PlanDTO> findAll() {
        List<Plan> planList = planRepository.findAll();
        return planList.stream()
                .map(plan -> planMapper.mapToPlanDTO(plan))
                .collect(Collectors.toList());
    }

    @Override
    public PlanDTO save(PlanDTO plan) {
        try {
            this.planValidate(plan, true, null);
            Plan planEntity = planMapper.mapToPlan(plan);
            Plan planDB = planRepository.save(planEntity);
            return planMapper.mapToPlanDTO(planDB);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Optional<PlanDTO> findById(String name) {
        return Optional.empty();
    }

    public Optional<PlanDTO> findByName(String name) {
        Optional<Plan> planEntity = planRepository.findByName(name);
        return planEntity.map(plan -> planMapper.mapToPlanDTO(plan));
    }

    public Optional<PlanDTO> findByNameWhenIsNotSame(String name, Long id) {
        Optional<Plan> planEntity = planRepository.findByNameWhenIsNotSame(name, id);
        return planEntity.map(plan -> planMapper.mapToPlanDTO(plan));
    }

    private void planValidate(PlanDTO plan, Boolean flgCreate, Long id) {

        if (plan.getPln_estado() == null || plan.getPln_estado().equals("")) {
            plan.setPln_estado(0L); //EstadoEnum.INACTIVO
        }

        if (plan.getPln_nombre() == null || plan.getPln_nombre() == "" || plan.getPln_nombre().isEmpty()) {
            throw new IllegalArgumentException("El campo nombre es obligatorio");
        } else {
            Optional<PlanDTO> planDB;

            if (Boolean.TRUE.equals(flgCreate)) {
                planDB = this.findByName(plan.getPln_nombre());
            } else {
                planDB = this.findByNameWhenIsNotSame(plan.getPln_nombre(), id);
            }

            if (planDB.isPresent()) {
                throw new IllegalArgumentException("Ya existe un registro con el mismo nombre del plan");
            }
        }

        if (LocalDate.parse(plan.getPln_fecha_inicio()).isAfter(LocalDate.parse(plan.getPln_fecha_fin()))) {
            throw new IllegalArgumentException("La fecha de inicio debe de ser mayor o igual a la fecha fin");
        }

        if (LocalDate.parse(plan.getPln_fecha_fin()).isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha fin debe de ser mayor o igual a la fecha actual");
        }
    }
}
