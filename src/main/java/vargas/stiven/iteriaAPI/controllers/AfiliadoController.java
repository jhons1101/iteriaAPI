package vargas.stiven.iteriaAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vargas.stiven.iteriaAPI.entity.Afiliado;
import vargas.stiven.iteriaAPI.services.afiliado.AfiliadoService;

import java.util.List;

@RestController
@RequestMapping("api/afiliado")
public class AfiliadoController {

    @Autowired
    AfiliadoService afiliadoService;

    @GetMapping("/")
    public List<Afiliado> findAll() {
        return afiliadoService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity save(@RequestBody Afiliado afiliado) throws Exception {
        try {
            Afiliado afiliadoDB = afiliadoService.save(afiliado);
            return ResponseEntity.status(201).body(afiliadoDB);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Afiliado afiliado) {
        try {
            Afiliado afiliadoDB = afiliadoService.update(id, afiliado);
            return ResponseEntity.status(200).body(afiliadoDB);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
