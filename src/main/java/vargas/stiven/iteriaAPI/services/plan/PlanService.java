package vargas.stiven.iteriaAPI.services.plan;

import vargas.stiven.iteriaAPI.entity.Plan;

import java.util.List;

public interface PlanService {

    List<Plan> findAll();

    Plan save(Plan plan);
}
