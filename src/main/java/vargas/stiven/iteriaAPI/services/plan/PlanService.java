package vargas.stiven.iteriaAPI.services.plan;

import vargas.stiven.iteriaAPI.dto.PlanDTO;
import vargas.stiven.iteriaAPI.entity.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanService {

    List<PlanDTO> findAll();

    PlanDTO save(PlanDTO plan);

    Optional<PlanDTO> findById(String name);
}
