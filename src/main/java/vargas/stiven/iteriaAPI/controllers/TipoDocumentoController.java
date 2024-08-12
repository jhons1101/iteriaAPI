package vargas.stiven.iteriaAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vargas.stiven.iteriaAPI.entity.TipoDocumento;
import vargas.stiven.iteriaAPI.services.tipoDocumento.TipoDocumentoService;

import java.util.List;

@RestController
@RequestMapping("/api/tipoDocumento")
public class TipoDocumentoController {

    @Autowired
    TipoDocumentoService tipoDocumentoService;

    @GetMapping("/")
    List<TipoDocumento> findAll() {
        return tipoDocumentoService.findAll();
    }

    @PostMapping("/")
    ResponseEntity<TipoDocumento> save(@RequestBody TipoDocumento tipoDoc) {
        TipoDocumento tipoDocDB = tipoDocumentoService.save(tipoDoc);
        return ResponseEntity.status(201).body(tipoDocDB);
    }
}
