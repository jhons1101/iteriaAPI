package vargas.stiven.iteriaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vargas.stiven.iteriaAPI.entity.Plan;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;

import java.util.Optional;


@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Override
    Optional<Plan> findById(Long id);

    @Query(value = "SELECT * FROM plan WHERE pln_nombre = ?1", nativeQuery = true)
    Optional<Plan> findByName(String name);

    @Query(value = "SELECT * FROM plan WHERE pln_nombre = ?1 AND pln_id <> ?2", nativeQuery = true)
    Optional<Plan> findByNameWhenIsNotSame(String name, Long plnId);
}
