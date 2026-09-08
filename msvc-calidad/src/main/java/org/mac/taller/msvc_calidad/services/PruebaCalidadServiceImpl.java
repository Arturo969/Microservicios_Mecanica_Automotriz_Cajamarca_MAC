package org.mac.taller.msvc_calidad.services;
import org.mac.taller.msvc_calidad.models.entity.PruebaCalidad;
import org.mac.taller.msvc_calidad.repositories.PruebaCalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PruebaCalidadServiceImpl implements PruebaCalidadService {
    @Autowired private PruebaCalidadRepository repository;
    @Override @Transactional(readOnly = true)
    public List<PruebaCalidad> listar() { return (List<PruebaCalidad>) repository.findAll(); }
    @Override @Transactional(readOnly = true)
    public Optional<PruebaCalidad> porId(Long id) { return repository.findById(id); }
    @Override @Transactional
    public PruebaCalidad guardar(PruebaCalidad p) { return repository.save(p); }
}