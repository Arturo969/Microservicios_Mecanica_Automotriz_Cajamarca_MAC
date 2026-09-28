package org.mac.taller.msvc_orden_trabajo.application.services;

import org.mac.taller.msvc_orden_trabajo.application.port.OrdenTrabajoRepositoryPort;
import org.mac.taller.msvc_orden_trabajo.domain.model.OrdenBahia;
import org.mac.taller.msvc_orden_trabajo.domain.model.OrdenTrabajo;
import org.mac.taller.msvc_orden_trabajo.infrastructure.clients.BahiaClientRest;
import org.mac.taller.msvc_orden_trabajo.infrastructure.clients.TecnicoClientRest;
import org.mac.taller.msvc_orden_trabajo.infrastructure.entities.OrdenBahiaEntity;
import org.mac.taller.msvc_orden_trabajo.infrastructure.entities.OrdenTrabajoEntity;
import org.mac.taller.msvc_orden_trabajo.infrastructure.repositories.OrdenTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenTrabajoServiceImpl implements OrdenTrabajoService {

    @Autowired
    private OrdenTrabajoRepositoryPort repositoryPort;
    
    @Autowired
    private BahiaClientRest bahiaClient;

    @Autowired
    private TecnicoClientRest tecnicoClient;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenTrabajo> listar() {
        return (List<OrdenTrabajo>) repositoryPort.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrdenTrabajo> porId(Long id) {
        return repositoryPort.buscarPorId(id);
    }

    @Override
    @Transactional
    public OrdenTrabajo guardar(OrdenTrabajo o) {
        return repositoryPort.guardar(o);
    }

    @Override
    @Transactional
    public OrdenTrabajo asignarABahia(Long ordenId, Long bahiaId) {
        OrdenTrabajo orden = repositoryPort.buscarPorId(ordenId)
            .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"));
        
        // 1. Validar y ocupar remotamente la bahía mediante Feign
        bahiaClient.detalle(bahiaId); // Verifica que exista
        bahiaClient.ocuparBahia(bahiaId); // Lanza error si ya está ocupada
        
        // 2. Liberar bahía anterior remotamente si existe una asignación ACTIVA
        orden.getHistorialBahias().stream()
             .filter(b -> "ACTIVA".equals(b.getEstadoAsignacion()))
             .forEach(b -> bahiaClient.liberarBahia(b.getBahiaId()));

        // 3. Crear nueva asignación en la tabla intermedia
        OrdenBahia nuevaAsignacion = new OrdenBahia(bahiaId);
        orden.asignarBahia(nuevaAsignacion);
        
        return repositoryPort.guardar(orden);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repositoryPort.eliminar(id);
    }

    @Override
    @Transactional
    public void eliminarAsignacionesPorBahia(Long bahiaId) {
        List<OrdenTrabajo> ordenes = (List<OrdenTrabajo>) repositoryPort.listar();
        for(OrdenTrabajo o : ordenes) {
            o.getHistorialBahias().removeIf(b -> b.getBahiaId().equals(bahiaId));
            repositoryPort.guardar(o);
        }
    }
}