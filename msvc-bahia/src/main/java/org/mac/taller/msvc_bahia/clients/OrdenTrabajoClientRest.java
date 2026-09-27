package org.mac.taller.msvc_bahia.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-orden-trabajo", url="localhost:8008/api/orden-trabajo")
public interface OrdenTrabajoClientRest {
    @DeleteMapping("/eliminarAsignacionesXBahia/{id}")
    void eliminarAsignacionesPorBahia(@PathVariable("id") Long id);
}
