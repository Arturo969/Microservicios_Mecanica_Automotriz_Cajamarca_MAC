package org.mac.taller.msvc_comprobante.repositories;
import org.mac.taller.msvc_comprobante.models.entity.ComprobantePago;
import org.springframework.data.repository.CrudRepository;
public interface ComprobanteRepository extends CrudRepository<ComprobantePago, Long> {}