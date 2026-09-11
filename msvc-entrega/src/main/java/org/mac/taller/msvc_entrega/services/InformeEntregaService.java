package org.mac.taller.msvc_entrega.services;
import org.mac.taller.msvc_entrega.models.entity.InformeEntrega;
import java.util.List;
import java.util.Optional;
public interface InformeEntregaService {
    List<InformeEntrega> listar();
    Optional<InformeEntrega> porId(Long id);
    InformeEntrega guardar(InformeEntrega i);
}