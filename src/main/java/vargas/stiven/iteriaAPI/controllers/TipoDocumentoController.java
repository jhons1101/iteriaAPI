package vargas.stiven.iteriaAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vargas.stiven.iteriaAPI.dto.TipoDocumentoDTO;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;
import vargas.stiven.iteriaAPI.services.tipoDocumento.TipoDocumentoService;

import java.util.List;

@RestController
@RequestMapping("/api/tipoDocumento")
public class TipoDocumentoController {

    @Autowired
    TipoDocumentoService tipoDocumentoService;

    @GetMapping("/")
    List<TipoDocumentoDTO> findAll() {
        return tipoDocumentoService.findAll();
    }

    @PostMapping("/")
    ResponseEntity save(@RequestBody TipoDocumentoDTO tipDoc) {
        try {
            TipoDocumentoDTO tipDocDB = tipoDocumentoService.save(tipDoc);
            return ResponseEntity.status(201).body(tipDocDB);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
