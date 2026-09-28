package org.mac.taller.msvc_orden_trabajo.application.services;

import org.mac.taller.msvc_orden_trabajo.domain.model.OrdenTrabajo;
import org.mac.taller.msvc_orden_trabajo.infrastructure.entities.OrdenTrabajoEntity;
import java.util.List;
import java.util.Optional;

public interface OrdenTrabajoService {
    List<OrdenTrabajo> listar();
    Optional<OrdenTrabajo> porId(Long id);
    OrdenTrabajo guardar(OrdenTrabajo o);
    OrdenTrabajo asignarABahia(Long ordenId, Long bahiaId);
    void eliminar(Long id);
    void eliminarAsignacionesPorBahia(Long bahiaId);
}