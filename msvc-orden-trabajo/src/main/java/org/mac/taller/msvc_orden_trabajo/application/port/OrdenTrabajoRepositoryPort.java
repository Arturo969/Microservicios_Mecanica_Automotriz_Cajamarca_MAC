package org.mac.taller.msvc_orden_trabajo.application.port;

import org.mac.taller.msvc_orden_trabajo.domain.model.OrdenTrabajo;

import java.util.List;
import java.util.Optional;

public interface OrdenTrabajoRepositoryPort {
    OrdenTrabajo guardar(OrdenTrabajo ordenTrabajo);
    Optional<OrdenTrabajo> buscarPorId(Long id);
    List<OrdenTrabajo> listar();
    void eliminar(Long id);
}
