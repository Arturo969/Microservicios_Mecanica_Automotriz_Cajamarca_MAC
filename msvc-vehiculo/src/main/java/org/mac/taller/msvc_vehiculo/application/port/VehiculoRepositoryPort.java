package org.mac.taller.msvc_vehiculo.application.port;

import org.mac.taller.msvc_vehiculo.domain.model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepositoryPort {

    List<Vehiculo> listar();
    Vehiculo guardar(Vehiculo vehiculo);
    Optional<Vehiculo> buscarPorId(Long id);
    void eliminarPorId(Long id);
}
