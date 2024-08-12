package vargas.stiven.iteriaAPI.services.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vargas.stiven.iteriaAPI.entity.Plan;
import vargas.stiven.iteriaAPI.repository.PlanRepository;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    PlanRepository planRepository;

    @Override
    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    @Override
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }
}
