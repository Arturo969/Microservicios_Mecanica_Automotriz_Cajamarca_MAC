package org.mac.taller.msvc_tecnico.repositories;

import org.mac.taller.msvc_tecnico.models.entity.Tecnico;
import org.springframework.data.repository.CrudRepository;

public interface TecnicoRepository extends CrudRepository<Tecnico, Long> {
}