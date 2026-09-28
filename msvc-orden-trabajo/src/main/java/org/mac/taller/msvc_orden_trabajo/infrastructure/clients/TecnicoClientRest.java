package org.mac.taller.msvc_orden_trabajo.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "msvc-tecnico", url = "localhost:8012")
public interface TecnicoClientRest {

    @GetMapping("/api/tecnico/{id}")
    Object detalle(@PathVariable("id") Long id);

    @PutMapping("/api/tecnico/{id}/asignar")
    void asignarAOrden(@PathVariable("id") Long id);

    @PutMapping("/api/tecnico/{id}/liberar")
    void liberar(@PathVariable("id") Long id);
}