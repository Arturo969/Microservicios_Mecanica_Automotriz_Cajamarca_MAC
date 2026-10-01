package org.mac.taller.msvc_cotizacion.infraestructure.controller;

import org.mac.taller.msvc_cotizacion.domain.model.AutorizacionCliente;
import org.mac.taller.msvc_cotizacion.domain.model.Cotizacion;
import org.mac.taller.msvc_cotizacion.application.service.CotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cotizacion")
public class CotizacionController {
    @Autowired
    private CotizacionService service;

    @GetMapping
    public List<Cotizacion> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cotizacion c) {
        c.calcularTotalEstimado();
        return ResponseEntity.ok(service.guardar(c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Cotizacion> o = service.porId(id);
        if(o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<?> aprobar(@PathVariable Long id, @RequestBody AutorizacionCliente auth) {
        Optional<Cotizacion> o = service.porId(id);
        if(o.isPresent()) {
            Cotizacion c = o.get();
            try {
                c.aprobar(auth);
                return ResponseEntity.ok(service.guardar(c));
            } catch (IllegalStateException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Cotizacion cotAct, @PathVariable Long id) {
        Optional<Cotizacion> o = service.porId(id);
        if(o.isPresent()) {
            Cotizacion db = o.get();
            db.setVehiculoId(cotAct.getVehiculoId());
            db.setEstado(cotAct.getEstado());
            db.setDiasInactividadEstimados(cotAct.getDiasInactividadEstimados());
            db.setFechaCreacion(cotAct.getFechaCreacion());
            db.setTareas(cotAct.getTareas());

            return ResponseEntity.ok(service.guardar(db));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Cotizacion> o = service.porId(id);
        if(o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}