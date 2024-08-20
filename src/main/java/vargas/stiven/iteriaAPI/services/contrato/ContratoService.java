package vargas.stiven.iteriaAPI.services.contrato;

import vargas.stiven.iteriaAPI.dto.ContratoDTO;

import java.util.List;
import java.util.Optional;

public interface ContratoService {

    List<ContratoDTO> findAll();

    ContratoDTO save(ContratoDTO contratoDto) throws IllegalArgumentException;

    Optional<ContratoDTO> findByPlnIDAndByAfiId(ContratoDTO contratoDto);

    ContratoDTO update(Long id, ContratoDTO contratoDto) throws IllegalArgumentException;

    List<ContratoDTO> findByAfiId (Long afiId);

    void updateWithdrawalDateByAfiId(Long afiId);
}
