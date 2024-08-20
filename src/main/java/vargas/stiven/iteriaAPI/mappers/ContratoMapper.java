package vargas.stiven.iteriaAPI.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vargas.stiven.iteriaAPI.dto.ContratoDTO;
import vargas.stiven.iteriaAPI.entity.Afiliado;
import vargas.stiven.iteriaAPI.entity.Contrato;
import vargas.stiven.iteriaAPI.entity.Plan;
import vargas.stiven.iteriaAPI.repository.AfiliadoRepository;
import vargas.stiven.iteriaAPI.repository.PlanRepository;

import java.util.Optional;

@Component
public class ContratoMapper {
    
    @Autowired
    AfiliadoRepository afiliadoRepository;

    @Autowired
    PlanRepository planRepository;

    // Convert Contrato JPA Entity to ContratoDTO
    public ContratoDTO mapToContratoDTO(Contrato contrato){
        return new ContratoDTO(
                contrato.getCto_id(),
                contrato.getAfi_id().getAfi_id(),
                contrato.getPln_id().getPln_id(),
                contrato.getCto_cantidad_usuarios(),
                contrato.getCto_fecha_inicio(),
                contrato.getCto_fecha_retiro(),
                contrato.getCto_fecha_registro(),
                contrato.getCto_eps()
        );
    }

    // Convert ContratoDTO to Contrato JPA Entity
    public Contrato mapToContrato(ContratoDTO contratoDto){

        Optional<Afiliado> affiliatedDB = afiliadoRepository.findById(contratoDto.getAfi_id());
        Afiliado afiliado = new Afiliado(contratoDto.getAfi_id());

        if (affiliatedDB.isPresent()) {
            afiliado = affiliatedDB.get();
        }

        Optional<Plan> planDB = planRepository.findById(contratoDto.getPln_id());
        Plan plan = new Plan(contratoDto.getPln_id());

        if (planDB.isPresent()) {
            plan = planDB.get();
        }

        return new Contrato(
                contratoDto.getCto_id(),
                afiliado,
                plan,
                contratoDto.getCto_cantidad_usuarios(),
                contratoDto.getCto_fecha_inicio(),
                contratoDto.getCto_fecha_retiro(),
                contratoDto.getCto_fecha_registro(),
                contratoDto.getCto_eps()
        );
    }
}
