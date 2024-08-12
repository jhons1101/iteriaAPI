package vargas.stiven.iteriaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vargas.stiven.iteriaAPI.entity.Afiliado;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;

import java.util.Optional;

@Repository
public interface AfiliadoRepository extends JpaRepository<Afiliado, Long> {

    @Query("SELECT a FROM Afiliado a WHERE a.tdc_id = :tipoDoc AND a.afi_documento = :afiDoc")
    Optional<Afiliado> findByTpoDocAndDoc (@Param("tipoDoc") TipoDocumento tipoDoc, @Param("afiDoc")  String afiDoc);

    @Query(value = "SELECT * FROM Afiliado WHERE tdc_id = ?1 AND afi_documento = ?2 AND afi_id <> ?3", nativeQuery = true)
    Optional<Afiliado> findByTpoDocAndDocWhenIsNotSame (Long tipoDoc, String afiDoc, Long afiId);

}
