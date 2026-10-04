package org.mac.taller.msvc_tecnico.application.services;

import org.mac.taller.msvc_tecnico.domain.model.Tecnico;

import java.util.List;
import java.util.Optional;

public interface TecnicoService {
    List<Tecnico> listar();
    Optional<Tecnico> porId(Long id);
    Tecnico guardar(Tecnico tecnico);
    Tecnico editar(Long id, Tecnico tecnico);
    Tecnico asignarAOrden(Long id);
    Tecnico liberar(Long id);
    void eliminar(Long id);
}
