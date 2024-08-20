package vargas.stiven.iteriaAPI.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vargas.stiven.iteriaAPI.dto.PlanDTO;
import vargas.stiven.iteriaAPI.entity.EstadoEnum;
import vargas.stiven.iteriaAPI.entity.Plan;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;
import vargas.stiven.iteriaAPI.repository.TipoDocumentoRepository;

import java.util.Optional;

@Component
public class PlanMapper {
    
    // Convert Plan JPA Entity to PlanDTO
    public PlanDTO mapToPlanDTO(Plan plan){
        return new PlanDTO(
                plan.getPln_id(),
                plan.getPln_nombre(),
                plan.getPln_fecha_inicio(),
                plan.getPln_fecha_fin(),
                plan.getPln_estado()
        );
    }

    // Convert PlanDTO to Plan JPA Entity
    public Plan mapToPlan(PlanDTO planDto){

        return new Plan(
                planDto.getPln_id(),
                planDto.getPln_nombre(),
                planDto.getPln_fecha_inicio(),
                planDto.getPln_fecha_fin(),
                planDto.getPln_estado()
        );
    }
}
