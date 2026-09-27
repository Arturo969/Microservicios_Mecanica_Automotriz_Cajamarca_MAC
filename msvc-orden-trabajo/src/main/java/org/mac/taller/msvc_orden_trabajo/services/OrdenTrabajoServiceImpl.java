package org.mac.taller.msvc_orden_trabajo.services;

import org.mac.taller.msvc_orden_trabajo.clients.BahiaClientRest;
import org.mac.taller.msvc_orden_trabajo.clients.TecnicoClientRest;
import org.mac.taller.msvc_orden_trabajo.models.entity.OrdenBahia;
import org.mac.taller.msvc_orden_trabajo.models.entity.OrdenTrabajo;
import org.mac.taller.msvc_orden_trabajo.repositories.OrdenTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenTrabajoServiceImpl implements OrdenTrabajoService {

    @Autowired
    private OrdenTrabajoRepository repository;
    
    @Autowired
    private BahiaClientRest bahiaClient;

    @Autowired
    private TecnicoClientRest tecnicoClient;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenTrabajo> listar() {
        return (List<OrdenTrabajo>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrdenTrabajo> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public OrdenTrabajo guardar(OrdenTrabajo o) {
        return repository.save(o);
    }

    @Override
    @Transactional
    public OrdenTrabajo asignarABahia(Long ordenId, Long bahiaId) {
        OrdenTrabajo orden = repository.findById(ordenId)
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
        
        return repository.save(orden);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void eliminarAsignacionesPorBahia(Long bahiaId) {
        List<OrdenTrabajo> ordenes = (List<OrdenTrabajo>) repository.findAll();
        for(OrdenTrabajo o : ordenes) {
            o.getHistorialBahias().removeIf(b -> b.getBahiaId().equals(bahiaId));
            repository.save(o);
        }
    }
}