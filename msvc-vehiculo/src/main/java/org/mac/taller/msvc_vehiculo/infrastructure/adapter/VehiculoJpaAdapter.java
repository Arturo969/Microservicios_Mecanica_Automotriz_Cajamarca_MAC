package org.mac.taller.msvc_vehiculo.infrastructure.adapter;

import org.mac.taller.msvc_vehiculo.application.port.VehiculoRepositoryPort;
import org.mac.taller.msvc_vehiculo.domain.model.Vehiculo;
import org.mac.taller.msvc_vehiculo.infrastructure.entity.VehiculoEntity;
import org.mac.taller.msvc_vehiculo.infrastructure.repository.VehiculoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehiculoJpaAdapter implements VehiculoRepositoryPort {

    @Autowired
    VehiculoJpaRepository vehiculoJpaRepository;

    @Override
    public List<Vehiculo> listar() {
        return vehiculoJpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Vehiculo guardar(Vehiculo vehiculo) {
        VehiculoEntity entity = toEntity(vehiculo);
        VehiculoEntity guardado = vehiculoJpaRepository.save(entity);
        return toDomain(guardado);
    }

    @Override
    public Optional<Vehiculo> buscarPorId(Long id) {
        return vehiculoJpaRepository.findById(id).map(this::toDomain);
    }


    @Override
    public void eliminarPorId(Long id) {
       vehiculoJpaRepository.deleteById(id);
    }

    private Vehiculo toDomain(VehiculoEntity vehiculoEntity) {
        return new Vehiculo(vehiculoEntity.getId(), vehiculoEntity.getPlaca(), vehiculoEntity.getVin(),
                vehiculoEntity.getMarca(), vehiculoEntity.getModelo(), vehiculoEntity.getKilometrajeActual(),
                vehiculoEntity.getClienteId());

    }

    private VehiculoEntity toEntity(Vehiculo vehiculo) {
        return new  VehiculoEntity(vehiculo.getId(), vehiculo.getPlaca(), vehiculo.getVin(),
                vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getKilometrajeActual(),
                vehiculo.getClienteId());
    }
}