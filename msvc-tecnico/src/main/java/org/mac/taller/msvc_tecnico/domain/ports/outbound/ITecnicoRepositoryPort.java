package org.mac.taller.msvc_tecnico.domain.ports.outbound;

import org.mac.taller.msvc_tecnico.domain.model.Tecnico;

import java.util.List;
import java.util.Optional;

public interface ITecnicoRepositoryPort {
    List<Tecnico> findAll();
    Optional<Tecnico> findById(Long id);
    Tecnico save(Tecnico tecnico);
    void deleteById(Long id);
    boolean existsById(Long id);
}
