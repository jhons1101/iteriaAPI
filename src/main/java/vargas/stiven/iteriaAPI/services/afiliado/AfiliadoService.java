package vargas.stiven.iteriaAPI.services.afiliado;

import vargas.stiven.iteriaAPI.dto.AfiliadoDTO;
import vargas.stiven.iteriaAPI.entity.Afiliado;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AfiliadoService {

    List<AfiliadoDTO> findAll();

    AfiliadoDTO save(AfiliadoDTO affiliated) throws IllegalArgumentException;

    Optional<AfiliadoDTO> findByTpoDocAndDoc (AfiliadoDTO affiliated);

    AfiliadoDTO update(Long id, AfiliadoDTO affiliated) throws IllegalArgumentException;
}
