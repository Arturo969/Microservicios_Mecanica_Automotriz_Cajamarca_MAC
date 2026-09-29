package org.mac.taller.msvc_tecnico.infrastructure.adapters.outbound.persistence;

import org.mac.taller.msvc_tecnico.domain.model.Tecnico;
import org.mac.taller.msvc_tecnico.domain.ports.outbound.ITecnicoRepositoryPort;
import org.mac.taller.msvc_tecnico.infrastructure.adapters.outbound.persistence.entity.TecnicoEntity;
import org.mac.taller.msvc_tecnico.infrastructure.adapters.outbound.persistence.mapper.TecnicoPersistenceMapper;
import org.mac.taller.msvc_tecnico.infrastructure.adapters.outbound.persistence.repository.SpringDataTecnicoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TecnicoPersistenceAdapter implements ITecnicoRepositoryPort {

    private final SpringDataTecnicoRepository springDataTecnicoRepository;
    private final TecnicoPersistenceMapper mapper;

    public TecnicoPersistenceAdapter(SpringDataTecnicoRepository springDataTecnicoRepository,
                                     TecnicoPersistenceMapper mapper) {
        this.springDataTecnicoRepository = springDataTecnicoRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Tecnico> findAll() {
        return springDataTecnicoRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Tecnico> findById(Long id) {
        return springDataTecnicoRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Tecnico save(Tecnico tecnico) {
        TecnicoEntity entity = mapper.toEntity(tecnico);
        TecnicoEntity saved = springDataTecnicoRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        springDataTecnicoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springDataTecnicoRepository.existsById(id);
    }
}
