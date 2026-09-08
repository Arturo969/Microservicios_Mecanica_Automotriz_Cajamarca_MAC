package org.mac.taller.msvc_calidad.controllers;
import org.mac.taller.msvc_calidad.models.entity.PruebaCalidad;
import org.mac.taller.msvc_calidad.services.PruebaCalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calidad")
public class PruebaCalidadController {
    @Autowired private PruebaCalidadService service;

    @GetMapping
    public List<PruebaCalidad> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody PruebaCalidad p) {
        return ResponseEntity.ok(service.guardar(p));
    }

    @GetMapping("/{id}/dictamen")
    public ResponseEntity<?> dictamen(@PathVariable Long id) {
        Optional<PruebaCalidad> o = service.porId(id);
        if (o.isPresent()) {
            boolean aprobado = o.get().emitirDictamen();
            return ResponseEntity.ok("¿Vehículo Aprobado?: " + aprobado);
        }
        return ResponseEntity.notFound().build();
    }
}