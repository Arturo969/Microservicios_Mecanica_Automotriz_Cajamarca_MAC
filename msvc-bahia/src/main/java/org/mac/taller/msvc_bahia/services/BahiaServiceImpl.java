package org.mac.taller.msvc_bahia.services;

import org.mac.taller.msvc_bahia.models.entity.BahiaTrabajo;
import org.mac.taller.msvc_bahia.repositories.BahiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BahiaServiceImpl implements BahiaService {

    @Autowired
    private BahiaRepository repository;

    @Autowired
    private org.mac.taller.msvc_bahia.clients.OrdenTrabajoClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<BahiaTrabajo> listar() {
        return (List<BahiaTrabajo>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BahiaTrabajo> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public BahiaTrabajo guardar(BahiaTrabajo b) {
        return repository.save(b);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
        client.eliminarAsignacionesPorBahia(id);
    }
}