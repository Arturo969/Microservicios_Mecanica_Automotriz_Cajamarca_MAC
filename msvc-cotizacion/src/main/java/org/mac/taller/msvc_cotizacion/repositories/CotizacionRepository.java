package org.mac.taller.msvc_cotizacion.repositories;

import org.mac.taller.msvc_cotizacion.models.entity.Cotizacion;
import org.springframework.data.repository.CrudRepository;

public interface CotizacionRepository extends CrudRepository<Cotizacion, Long> {
}