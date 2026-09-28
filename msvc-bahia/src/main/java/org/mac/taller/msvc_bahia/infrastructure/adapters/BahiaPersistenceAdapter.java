package org.mac.taller.msvc_bahia.infrastructure.adapters;

import org.mac.taller.msvc_bahia.application.port.BahiaRepositoryPort;
import org.mac.taller.msvc_bahia.domain.model.BahiaTrabajo;
import org.mac.taller.msvc_bahia.infrastructure.entities.BahiaTrabajoEntity;
import org.mac.taller.msvc_bahia.infrastructure.repositories.BahiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BahiaPersistenceAdapter implements BahiaRepositoryPort {

    @Autowired
    private BahiaRepository repository;

    @Autowired
    private BahiaMapper mapper;

    @Override
    public BahiaTrabajo guardar(BahiaTrabajo bahia) {
        BahiaTrabajoEntity entity = mapper.toEntity(bahia);
        BahiaTrabajoEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<BahiaTrabajo> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<BahiaTrabajo> listar() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}