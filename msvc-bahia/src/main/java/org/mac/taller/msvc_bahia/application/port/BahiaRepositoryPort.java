package org.mac.taller.msvc_bahia.application.port;

import org.mac.taller.msvc_bahia.domain.model.BahiaTrabajo;

import java.util.List;
import java.util.Optional;

public interface BahiaRepositoryPort {
    BahiaTrabajo guardar(BahiaTrabajo bahia);
    Optional<BahiaTrabajo> buscarPorId(Long id);
    List<BahiaTrabajo> listar();
    void eliminar(Long id);
}