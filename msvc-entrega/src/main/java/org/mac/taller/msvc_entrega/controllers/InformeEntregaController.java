package org.mac.taller.msvc_entrega.controllers;
import org.mac.taller.msvc_entrega.models.entity.InformeEntrega;
import org.mac.taller.msvc_entrega.services.InformeEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrega")
public class InformeEntregaController {
    @Autowired private InformeEntregaService service;

    @GetMapping
    public List<InformeEntrega> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody InformeEntrega i) {
        try {
            return ResponseEntity.ok(service.guardar(i));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}