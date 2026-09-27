package org.mac.taller.msvc_vehiculo.services;

import org.mac.taller.msvc_vehiculo.models.entity.Vehiculo;
import java.util.List;
import java.util.Optional;

public interface VehiculoService {
    List<Vehiculo> listar();
    Optional<Vehiculo> porId(Long id);
    Vehiculo guardar(Vehiculo v);
    void eliminar(Long id);
}