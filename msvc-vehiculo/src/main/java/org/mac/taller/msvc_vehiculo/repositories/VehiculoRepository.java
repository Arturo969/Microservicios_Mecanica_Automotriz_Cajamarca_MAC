package org.mac.taller.msvc_vehiculo.repositories;

import org.mac.taller.msvc_vehiculo.models.entity.Vehiculo;
import org.springframework.data.repository.CrudRepository;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Long> {
}