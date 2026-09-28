package org.mac.taller.msvc_cotizacion.infraestructure.repository;

import org.mac.taller.msvc_cotizacion.infraestructure.entity.CotizacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotizacionJpaRepository extends JpaRepository<CotizacionEntity, Long> {
}