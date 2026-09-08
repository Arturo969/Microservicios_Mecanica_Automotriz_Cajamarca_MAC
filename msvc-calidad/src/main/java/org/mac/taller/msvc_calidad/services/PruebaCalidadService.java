package org.mac.taller.msvc_calidad.services;
import org.mac.taller.msvc_calidad.models.entity.PruebaCalidad;
import java.util.List;
import java.util.Optional;

public interface PruebaCalidadService {
    List<PruebaCalidad> listar();
    Optional<PruebaCalidad> porId(Long id);
    PruebaCalidad guardar(PruebaCalidad p);
}
