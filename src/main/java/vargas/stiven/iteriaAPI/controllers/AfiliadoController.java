package vargas.stiven.iteriaAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vargas.stiven.iteriaAPI.dto.AfiliadoDTO;
import vargas.stiven.iteriaAPI.entity.Afiliado;
import vargas.stiven.iteriaAPI.services.afiliado.AfiliadoService;

import java.util.List;

@RestController
@RequestMapping("api/afiliado")
public class AfiliadoController {

    @Autowired
    AfiliadoService afiliadoService;

    @GetMapping("/")
    public List<AfiliadoDTO> findAll() {
        return afiliadoService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity save(@RequestBody AfiliadoDTO affiliated) {
        try {
            AfiliadoDTO afiliadoDB = afiliadoService.save(affiliated);
            return ResponseEntity.status(201).body(afiliadoDB);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody AfiliadoDTO affiliated) {
        try {
            AfiliadoDTO affiliatedDB = afiliadoService.update(id, affiliated);
            return ResponseEntity.status(200).body(affiliatedDB);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
