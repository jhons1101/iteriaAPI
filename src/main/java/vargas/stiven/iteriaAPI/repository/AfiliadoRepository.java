package vargas.stiven.iteriaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vargas.stiven.iteriaAPI.entity.Afiliado;

import java.util.Optional;

@Repository
public interface AfiliadoRepository extends JpaRepository<Afiliado, Long> {

    @Query(value = "SELECT * FROM Afiliado WHERE tdc_id = ?1 AND afi_documento = ?2", nativeQuery = true)
    Optional<Afiliado> findByTpoDocAndDoc (Long tipDoc, String afiDoc);

    @Query(value = "SELECT * FROM Afiliado WHERE tdc_id = ?1 AND afi_documento = ?2 AND afi_id <> ?3", nativeQuery = true)
    Optional<Afiliado> findByTpoDocAndDocWhenIsNotSame (Long tipDoc, String afiDoc, Long afiId);

}
