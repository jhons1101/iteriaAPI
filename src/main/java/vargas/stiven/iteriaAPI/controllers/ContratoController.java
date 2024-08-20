package vargas.stiven.iteriaAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vargas.stiven.iteriaAPI.dto.ContratoDTO;
import vargas.stiven.iteriaAPI.services.contrato.ContratoService;

import java.util.List;

@RestController
@RequestMapping("api/contratoDto")
public class ContratoController {

    @Autowired
    ContratoService contratoService;

    @GetMapping("/")
    public List<ContratoDTO> findAll() {
        return contratoService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity save(@RequestBody ContratoDTO contratoDto) {
        try {
            ContratoDTO contratoDB = contratoService.save(contratoDto);
            return ResponseEntity.status(201).body(contratoDB);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ContratoDTO contratoDto) {
       try {
           ContratoDTO contratoDB = contratoService.update(id, contratoDto);
           return ResponseEntity.status(201).body(contratoDB);
       } catch (IllegalArgumentException e) {
           return ResponseEntity.status(400).body(e.getMessage());
       }
    }
}
