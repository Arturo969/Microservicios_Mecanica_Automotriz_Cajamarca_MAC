package org.mac.taller.msvc_orden_trabajo.infrastructure.adapters;

import org.mac.taller.msvc_orden_trabajo.application.port.OrdenTrabajoRepositoryPort;
import org.mac.taller.msvc_orden_trabajo.domain.model.OrdenTrabajo;
import org.mac.taller.msvc_orden_trabajo.infrastructure.entities.OrdenTrabajoEntity;
import org.mac.taller.msvc_orden_trabajo.infrastructure.repositories.OrdenTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class OrdenTrabajoPersistenceAdapter implements OrdenTrabajoRepositoryPort {
    @Autowired
    private OrdenTrabajoRepository repository;

    @Autowired
    private OrdenTrabajoMapper mapper;

    @Override
    public OrdenTrabajo guardar(OrdenTrabajo ordenTrabajo) {
        OrdenTrabajoEntity entity = mapper.toEntity(ordenTrabajo);
        OrdenTrabajoEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<OrdenTrabajo> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<OrdenTrabajo> listar() {
        return ((List<OrdenTrabajoEntity>) repository.findAll())
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
