package org.mac.taller.msvc_cotizacion.application.port;

import org.mac.taller.msvc_cotizacion.domain.model.Cotizacion;

import java.util.List;
import java.util.Optional;

public interface CotizacionRepositoryPort {
    List<Cotizacion> findAll();
    Optional<Cotizacion> findById(Long id);
    Cotizacion guardar(Cotizacion cotizacion);
    void deleteById(Long id);
}
