package org.mac.taller.msvc_tecnico.services;

import org.mac.taller.msvc_tecnico.models.entity.Tecnico;
import java.util.List;
import java.util.Optional;

public interface TecnicoService {
    List<Tecnico> listar();
    Optional<Tecnico> porId(Long id);
    Tecnico guardar(Tecnico t);
    void eliminar(Long id);
}