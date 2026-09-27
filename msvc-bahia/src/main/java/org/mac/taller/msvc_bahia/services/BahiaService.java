package org.mac.taller.msvc_bahia.services;

import org.mac.taller.msvc_bahia.models.entity.BahiaTrabajo;
import java.util.List;
import java.util.Optional;

public interface BahiaService {
    List<BahiaTrabajo> listar();
    Optional<BahiaTrabajo> porId(Long id);
    BahiaTrabajo guardar(BahiaTrabajo b);
    void eliminar(Long id);
}