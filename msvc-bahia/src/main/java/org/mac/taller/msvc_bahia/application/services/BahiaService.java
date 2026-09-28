package org.mac.taller.msvc_bahia.application.services;

import org.mac.taller.msvc_bahia.domain.model.BahiaTrabajo;

import java.util.List;
import java.util.Optional;

public interface BahiaService {
    List<BahiaTrabajo> listar();
    Optional<BahiaTrabajo> porId(Long id);
    BahiaTrabajo guardar(BahiaTrabajo b);
    void eliminar(Long id);
}