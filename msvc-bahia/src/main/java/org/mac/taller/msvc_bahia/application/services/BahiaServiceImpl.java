package org.mac.taller.msvc_bahia.application.services;

import org.mac.taller.msvc_bahia.application.port.BahiaRepositoryPort;
import org.mac.taller.msvc_bahia.domain.model.BahiaTrabajo;
import org.mac.taller.msvc_bahia.infrastructure.clients.OrdenTrabajoClientRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BahiaServiceImpl implements BahiaService {

    @Autowired
    private BahiaRepositoryPort repositoryPort;

    @Autowired
    private OrdenTrabajoClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<BahiaTrabajo> listar() {
        return repositoryPort.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BahiaTrabajo> porId(Long id) {
        return repositoryPort.buscarPorId(id);
    }

    @Override
    @Transactional
    public BahiaTrabajo guardar(BahiaTrabajo b) {
        return repositoryPort.guardar(b);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repositoryPort.eliminar(id);
        client.eliminarAsignacionesPorBahia(id);
    }
}