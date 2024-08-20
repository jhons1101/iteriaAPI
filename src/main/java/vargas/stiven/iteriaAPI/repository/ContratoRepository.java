package vargas.stiven.iteriaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vargas.stiven.iteriaAPI.entity.Contrato;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    @Query(value = "SELECT * FROM contrato WHERE pln_id = :plnId AND afi_id = :afiId", nativeQuery = true)
    Optional<Contrato> findByPlnIDAndByAfiId(@Param("plnId") Long plnId, @Param("afiId") Long afiId);

    @Query(value = "SELECT a.* FROM contrato a WHERE a.pln_id = ?1 AND a.afi_id = ?2 AND a.cto_id <> ?3", nativeQuery = true)
    Optional<Contrato> findByPlnIDAndByAfiIdAndCtoId(Long plnId, Long afiId, Long ctoId);

    @Query(value = "SELECT a.* FROM contrato a WHERE a.afi_id = ?1", nativeQuery = true)
    List<Contrato> findByAfiId(Long afiId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE contrato SET cto_fecha_retiro = CURDATE() WHERE afi_id = ?1", nativeQuery = true)
    void updateWithdrawalDateByAfiId(Long afiId);
}
