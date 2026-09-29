package org.mac.taller.msvc_tecnico.infrastructure.adapters.outbound.persistence.repository;

import org.mac.taller.msvc_tecnico.infrastructure.adapters.outbound.persistence.entity.TecnicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataTecnicoRepository extends JpaRepository<TecnicoEntity, Long> {
}
