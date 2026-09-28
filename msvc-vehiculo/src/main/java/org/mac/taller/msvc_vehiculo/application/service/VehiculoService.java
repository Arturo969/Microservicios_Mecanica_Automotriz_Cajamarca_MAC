package org.mac.taller.msvc_vehiculo.application.service;

import org.mac.taller.msvc_vehiculo.domain.model.Vehiculo;
import java.util.List;
import java.util.Optional;

public interface VehiculoService {
    List<Vehiculo> listar();
    Optional<Vehiculo> porId(Long id);
    Vehiculo guardar(Vehiculo vehiculo);
    void eliminar(Long id);
}