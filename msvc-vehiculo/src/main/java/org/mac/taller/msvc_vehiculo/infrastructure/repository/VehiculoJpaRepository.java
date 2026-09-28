package org.mac.taller.msvc_vehiculo.infrastructure.repository;

import org.mac.taller.msvc_vehiculo.infrastructure.entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoJpaRepository extends JpaRepository<VehiculoEntity, Long> {
}