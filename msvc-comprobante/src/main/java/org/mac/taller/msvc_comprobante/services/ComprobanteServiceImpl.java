package org.mac.taller.msvc_comprobante.services;
import org.mac.taller.msvc_comprobante.models.entity.ComprobantePago;
import org.mac.taller.msvc_comprobante.repositories.ComprobanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ComprobanteServiceImpl implements ComprobanteService {
    @Autowired private ComprobanteRepository repository;
    @Override @Transactional(readOnly = true)
    public List<ComprobantePago> listar() { return (List<ComprobantePago>) repository.findAll(); }
    @Override @Transactional(readOnly = true)
    public Optional<ComprobantePago> porId(Long id) { return repository.findById(id); }
    @Override @Transactional
    public ComprobantePago guardar(ComprobantePago c) { return repository.save(c); }
}