package vargas.stiven.iteriaAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vargas.stiven.iteriaAPI.entity.Contrato;
import vargas.stiven.iteriaAPI.services.contrato.ContratoService;

import java.util.List;

@RestController
@RequestMapping("api/contrato")
public class ContratoController {

    @Autowired
    ContratoService contratoService;

    @GetMapping("/")
    public List<Contrato> findAll() {
        return contratoService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity save(@RequestBody Contrato contrato) {
        try {
            Contrato contratoDB = contratoService.save(contrato);
            return ResponseEntity.status(201).body(contratoDB);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Contrato contrato) throws Exception {
       try {
           Contrato contratoDB = contratoService.update(id, contrato);
           return ResponseEntity.status(201).body(contratoDB);
       } catch (Exception e) {
           return ResponseEntity.status(400).body(e.getMessage());
       }
    }
}
