package org.mac.taller.msvc_tecnico.infrastructure.repositories;

import org.mac.taller.msvc_tecnico.infrastructure.entities.TecnicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataTecnicoRepository extends JpaRepository<TecnicoEntity, Long> {
}
