package org.mac.taller.msvc_comprobante.controllers;
import org.mac.taller.msvc_comprobante.models.entity.ComprobantePago;
import org.mac.taller.msvc_comprobante.services.ComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comprobante")
public class ComprobanteController {
    @Autowired private ComprobanteService service;

    @GetMapping
    public List<ComprobantePago> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ComprobantePago c) {
        return ResponseEntity.ok(service.guardar(c));
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<?> totalConIgv(@PathVariable Long id) {
        Optional<ComprobantePago> o = service.porId(id);
        if (o.isPresent()) {
            return ResponseEntity.ok(o.get().calcularTotalConIgv()); // Invariante
        }
        return ResponseEntity.notFound().build();
    }
}