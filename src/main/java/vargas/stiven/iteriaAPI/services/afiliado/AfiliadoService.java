package vargas.stiven.iteriaAPI.services.afiliado;

import vargas.stiven.iteriaAPI.entity.Afiliado;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AfiliadoService {

    List<Afiliado> findAll();

    Afiliado save(Afiliado afiliado) throws Exception;

    Optional<Afiliado> findByTpoDocAndDoc (Afiliado afiliado);

    Afiliado update(Long id, Afiliado afiliado) throws Exception;
}
