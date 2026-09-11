package org.mac.taller.msvc_comprobante.services;
import org.mac.taller.msvc_comprobante.models.entity.ComprobantePago;
import java.util.List;
import java.util.Optional;
public interface ComprobanteService {
    List<ComprobantePago> listar();
    Optional<ComprobantePago> porId(Long id);
    ComprobantePago guardar(ComprobantePago c);
}