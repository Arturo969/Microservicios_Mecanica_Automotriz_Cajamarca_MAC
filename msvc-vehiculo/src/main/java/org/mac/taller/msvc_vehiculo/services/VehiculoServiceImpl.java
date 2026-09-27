package org.mac.taller.msvc_vehiculo.services;

import org.mac.taller.msvc_vehiculo.models.entity.Vehiculo;
import org.mac.taller.msvc_vehiculo.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {
    @Autowired
    private VehiculoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> listar() { return (List<Vehiculo>) repository.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehiculo> porId(Long id) { return repository.findById(id); }

    @Override
    @Transactional
    public Vehiculo guardar(Vehiculo v) { return repository.save(v); }

    @Override
    @Transactional
    public void eliminar(Long id) { repository.deleteById(id); }
}