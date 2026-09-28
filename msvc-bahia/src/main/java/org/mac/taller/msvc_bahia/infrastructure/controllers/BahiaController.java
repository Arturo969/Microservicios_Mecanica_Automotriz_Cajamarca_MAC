package org.mac.taller.msvc_bahia.infrastructure.controllers;

import org.mac.taller.msvc_bahia.application.services.BahiaService;
import org.mac.taller.msvc_bahia.domain.model.BahiaTrabajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bahia")
public class BahiaController {

    @Autowired
    private BahiaService service;

    @GetMapping
    public List<BahiaTrabajo> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody BahiaTrabajo b) {
        return ResponseEntity.ok(service.guardar(b));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<BahiaTrabajo> o = service.porId(id);
        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarBahia(@RequestBody BahiaTrabajo bahiaActualizada, @PathVariable Long id) {
        Optional<BahiaTrabajo> o = service.porId(id);
        if (o.isPresent()) {
            BahiaTrabajo bahiaDB = o.get();
            bahiaDB.setCodigo(bahiaActualizada.getCodigo());
            bahiaDB.setTipo(bahiaActualizada.getTipo());
            return ResponseEntity.ok(service.guardar(bahiaDB));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/ocupar")
    public ResponseEntity<?> ocupar(@PathVariable Long id) {
        Optional<BahiaTrabajo> o = service.porId(id);
        if (o.isPresent()) {
            BahiaTrabajo b = o.get();
            try {
                b.ocupar();
                return ResponseEntity.ok(service.guardar(b));
            } catch (IllegalStateException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/liberar")
    public ResponseEntity<?> liberar(@PathVariable Long id) {
        Optional<BahiaTrabajo> o = service.porId(id);
        if (o.isPresent()) {
            BahiaTrabajo b = o.get();
            b.liberar();
            return ResponseEntity.ok(service.guardar(b));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<BahiaTrabajo> o = service.porId(id);
        if (o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}