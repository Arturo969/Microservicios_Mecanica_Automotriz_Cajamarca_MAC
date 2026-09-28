package org.mac.taller.msvc_vehiculo.application.service;

import org.mac.taller.msvc_vehiculo.application.port.VehiculoRepositoryPort;
import org.mac.taller.msvc_vehiculo.domain.model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepositoryPort vehiculoRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> listar() {
        return vehiculoRepositoryPort.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehiculo> porId(Long id) {
        return vehiculoRepositoryPort.buscarPorId(id);
    }

    @Override
    @Transactional
    public Vehiculo guardar(Vehiculo vehiculo) {
        return vehiculoRepositoryPort.guardar(vehiculo);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        vehiculoRepositoryPort.eliminarPorId(id);
    }
}