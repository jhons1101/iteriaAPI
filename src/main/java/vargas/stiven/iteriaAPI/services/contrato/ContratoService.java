package vargas.stiven.iteriaAPI.services.contrato;

import vargas.stiven.iteriaAPI.entity.Contrato;

import java.util.List;
import java.util.Optional;

public interface ContratoService {

    List<Contrato> findAll();

    Contrato save(Contrato contrato) throws Exception;

    Optional<Contrato> findByPlnIDAndByAfiId(Contrato contrato);

    Contrato update(Long id, Contrato contrato) throws Exception;

    List<Contrato> findByAfiId (Long afiId);

    void updateWithdrawalDateByAfiId(Long afiId);
}
