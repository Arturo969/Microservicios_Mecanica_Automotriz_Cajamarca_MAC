package org.mac.taller.msvc_tecnico.services;

import org.mac.taller.msvc_tecnico.models.entity.Tecnico;
import org.mac.taller.msvc_tecnico.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoServiceImpl implements TecnicoService {
    @Autowired
    private TecnicoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Tecnico> listar() { return (List<Tecnico>) repository.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tecnico> porId(Long id) { return repository.findById(id); }

    @Override
    @Transactional
    public Tecnico guardar(Tecnico t) { return repository.save(t); }

    @Override
    @Transactional
    public void eliminar(Long id) { repository.deleteById(id); }
}