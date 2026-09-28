package org.mac.taller.msvc_orden_trabajo.infrastructure.controllers;

import org.mac.taller.msvc_orden_trabajo.domain.model.OrdenTrabajo;
import org.mac.taller.msvc_orden_trabajo.infrastructure.entities.OrdenTrabajoEntity;
import org.mac.taller.msvc_orden_trabajo.application.services.OrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orden-trabajo")
public class OrdenTrabajoController {

    @Autowired
    private OrdenTrabajoService service;
    
    @GetMapping
    public List<OrdenTrabajo> listar() {
        return service.listar();
    }
    
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody OrdenTrabajo o) {
        return ResponseEntity.ok(service.guardar(o));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<OrdenTrabajo> o = service.porId(id);
        if(o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarOrden(@RequestBody OrdenTrabajo ordenActualizada, @PathVariable Long id) {
        Optional<OrdenTrabajo> o = service.porId(id);
        if(o.isPresent()) {
            OrdenTrabajo ordenDB = o.get();
            // Permite sobreescribir la lista de tareas genéricamente
            
            ordenDB.setCotizacionId(ordenActualizada.getCotizacionId());
            ordenDB.setVehiculoId(ordenActualizada.getVehiculoId());
            ordenDB.setTecnicoAsignadoId(ordenActualizada.getTecnicoAsignadoId());
            ordenDB.setEstado(ordenActualizada.getEstado());
            ordenDB.setCerrada(ordenActualizada.isCerrada());
            
            ordenDB.getTareas().clear();
            ordenDB.getTareas().addAll(ordenActualizada.getTareas());

            return ResponseEntity.ok(service.guardar(ordenDB));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/asignar-bahia")
    public ResponseEntity<?> asignarBahia(@PathVariable Long id, @RequestParam Long bahiaId) {
        try {
            OrdenTrabajo actualizada = service.asignarABahia(id, bahiaId);
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/cerrar")
    public ResponseEntity<?> cerrarOrden(@PathVariable Long id) {
        Optional<OrdenTrabajo> opt = service.porId(id);
        if(opt.isPresent()){
            OrdenTrabajo o = opt.get();
            try {
                o.cerrarOrden();
                return ResponseEntity.ok(service.guardar(o));
            } catch (IllegalStateException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<OrdenTrabajo> o = service.porId(id);
        if(o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminarAsignacionesXBahia/{bahiaId}")
    public ResponseEntity<?> eliminarAsignacionesPorBahia(@PathVariable Long bahiaId) {
        service.eliminarAsignacionesPorBahia(bahiaId);
        return ResponseEntity.noContent().build();
    }
}