package org.mac.taller.msvc_tecnico.controllers;

import org.mac.taller.msvc_tecnico.models.entity.Tecnico;
import org.mac.taller.msvc_tecnico.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tecnico")
public class TecnicoController {
    @Autowired
    private TecnicoService service;

    @GetMapping
    public List<Tecnico> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Tecnico t) {
        return ResponseEntity.ok(service.guardar(t));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Tecnico> o = service.porId(id);
        if(o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/asignar")
    public ResponseEntity<?> asignarAOrden(@PathVariable Long id) {
        Optional<Tecnico> o = service.porId(id);
        if(o.isPresent()) {
            Tecnico t = o.get();
            try {
                t.asignarAOrden();
                return ResponseEntity.ok(service.guardar(t));
            } catch (IllegalStateException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/liberar")
    public ResponseEntity<?> liberar(@PathVariable Long id) {
        Optional<Tecnico> o = service.porId(id);
        if(o.isPresent()) {
            Tecnico t = o.get();
            t.liberarTecnico();
            return ResponseEntity.ok(service.guardar(t));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Tecnico tecAct, @PathVariable Long id) {
        Optional<Tecnico> o = service.porId(id);
        if(o.isPresent()) {
            Tecnico db = o.get();
            db.setDni(tecAct.getDni());
            db.setNombreCompleto(tecAct.getNombreCompleto());
            db.setEstado(tecAct.getEstado());
            db.setTurnoLaboral(tecAct.getTurnoLaboral());
            db.getEspecialidades().clear();
            db.getEspecialidades().addAll(tecAct.getEspecialidades());
            return ResponseEntity.ok(service.guardar(db));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Tecnico> o = service.porId(id);
        if(o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}