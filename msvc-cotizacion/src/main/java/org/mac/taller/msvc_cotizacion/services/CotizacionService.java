package org.mac.taller.msvc_cotizacion.services;

import org.mac.taller.msvc_cotizacion.models.entity.Cotizacion;
import java.util.List;
import java.util.Optional;

public interface CotizacionService {
    List<Cotizacion> listar();
    Optional<Cotizacion> porId(Long id);
    Cotizacion guardar(Cotizacion c);
    void eliminar(Long id);
}