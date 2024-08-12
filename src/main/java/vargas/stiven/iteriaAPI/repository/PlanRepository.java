package vargas.stiven.iteriaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vargas.stiven.iteriaAPI.entity.Plan;


@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
