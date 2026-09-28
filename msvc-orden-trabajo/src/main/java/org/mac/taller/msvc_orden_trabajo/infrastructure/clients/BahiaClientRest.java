package org.mac.taller.msvc_orden_trabajo.infrastructure.clients;

import org.mac.taller.msvc_orden_trabajo.infrastructure.clients.dto.BahiaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "msvc-bahia", url="localhost:8001/api/bahia")
public interface BahiaClientRest {
    @GetMapping("/{id}")
    BahiaDTO detalle(@PathVariable("id") Long id);
    
    @PutMapping("/{id}/ocupar")
    void ocuparBahia(@PathVariable("id") Long id);
    
    @PutMapping("/{id}/liberar")
    void liberarBahia(@PathVariable("id") Long id);
}