package org.mac.taller.msvc_entrega.services;
import org.mac.taller.msvc_entrega.models.entity.InformeEntrega;
import org.mac.taller.msvc_entrega.repositories.InformeEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InformeEntregaServiceImpl implements InformeEntregaService {
    @Autowired private InformeEntregaRepository repository;
    @Override @Transactional(readOnly = true)
    public List<InformeEntrega> listar() { return (List<InformeEntrega>) repository.findAll(); }
    @Override @Transactional(readOnly = true)
    public Optional<InformeEntrega> porId(Long id) { return repository.findById(id); }
    @Override @Transactional
    public InformeEntrega guardar(InformeEntrega i) { return repository.save(i); }
}