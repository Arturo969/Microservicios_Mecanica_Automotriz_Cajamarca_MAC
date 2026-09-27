package org.mac.taller.msvc_cotizacion.services;

import org.mac.taller.msvc_cotizacion.models.entity.Cotizacion;
import org.mac.taller.msvc_cotizacion.repositories.CotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CotizacionServiceImpl implements CotizacionService {
    @Autowired
    private CotizacionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Cotizacion> listar() { return (List<Cotizacion>) repository.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cotizacion> porId(Long id) { return repository.findById(id); }

    @Override
    @Transactional
    public Cotizacion guardar(Cotizacion c) { return repository.save(c); }

    @Override
    @Transactional
    public void eliminar(Long id) { repository.deleteById(id); }
}