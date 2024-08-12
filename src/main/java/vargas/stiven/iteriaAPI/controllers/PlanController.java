package vargas.stiven.iteriaAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vargas.stiven.iteriaAPI.entity.Plan;
import vargas.stiven.iteriaAPI.services.plan.PlanService;

import java.util.List;

@RestController
@RequestMapping("api/plan")
public class PlanController {

    @Autowired
    PlanService planService;

    @GetMapping("/")
    public List<Plan> findAll() {
        return planService.findAll();
    }

    @PostMapping("/")
    ResponseEntity save(@RequestBody Plan plan) {
        try {
            Plan planDB = planService.save(plan);
            return ResponseEntity.status(201).body(planDB);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
