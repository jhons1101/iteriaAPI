package vargas.stiven.iteriaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;

import java.util.Optional;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {

    @Override
    Optional<TipoDocumento> findById(Long id);

    @Query(value = "SELECT * FROM tipo_documento WHERE tdc_nombre = ?1", nativeQuery = true)
    Optional<TipoDocumento> findByName(String name);

    @Query(value = "SELECT * FROM tipo_documento WHERE tdc_nombre = ?1 AND tdc_id <> ?2", nativeQuery = true)
    Optional<TipoDocumento> findByNameWhenIsNotSame(String name, Long tdcId);
}
