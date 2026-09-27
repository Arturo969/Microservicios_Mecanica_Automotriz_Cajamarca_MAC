package org.mac.taller.msvc_vehiculo.controllers;

import org.mac.taller.msvc_vehiculo.models.entity.Vehiculo;
import org.mac.taller.msvc_vehiculo.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {
    @Autowired
    private VehiculoService service;

    @GetMapping
    public List<Vehiculo> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Vehiculo v) {
        return ResponseEntity.ok(service.guardar(v));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Vehiculo> o = service.porId(id);
        if(o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Vehiculo vehiculoAct, @PathVariable Long id) {
        Optional<Vehiculo> o = service.porId(id);
        if(o.isPresent()) {
            Vehiculo db = o.get();
            db.setMarca(vehiculoAct.getMarca());
            db.setModelo(vehiculoAct.getModelo());
            db.setVin(vehiculoAct.getVin());
            db.setPlaca(vehiculoAct.getPlaca());
            db.setClienteId(vehiculoAct.getClienteId());
            try {
                if(vehiculoAct.getKilometrajeActual() != null) {
                    db.actualizarKilometraje(vehiculoAct.getKilometrajeActual());
                }
                return ResponseEntity.ok(service.guardar(db));
            } catch (IllegalStateException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Vehiculo> o = service.porId(id);
        if(o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}