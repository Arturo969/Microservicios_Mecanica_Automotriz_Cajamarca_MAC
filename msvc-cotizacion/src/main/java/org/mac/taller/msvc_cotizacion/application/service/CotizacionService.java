package org.mac.taller.msvc_cotizacion.application.service;

import org.mac.taller.msvc_cotizacion.domain.model.Cotizacion;
import java.util.List;
import java.util.Optional;

public interface CotizacionService {
    List<Cotizacion> listar();
    Optional<Cotizacion> porId(Long id);
    Cotizacion guardar(Cotizacion c);
    void eliminar(Long id);
}