package org.mac.taller.msvc_orden_trabajo.infrastructure.repositories;

import org.mac.taller.msvc_orden_trabajo.infrastructure.entities.OrdenTrabajoEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrdenTrabajoRepository extends CrudRepository<OrdenTrabajoEntity, Long> {
}