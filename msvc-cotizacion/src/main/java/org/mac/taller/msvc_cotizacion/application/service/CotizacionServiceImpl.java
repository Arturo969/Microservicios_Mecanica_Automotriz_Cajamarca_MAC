package org.mac.taller.msvc_cotizacion.application.service;

import org.mac.taller.msvc_cotizacion.application.port.CotizacionRepositoryPort;
import org.mac.taller.msvc_cotizacion.domain.model.Cotizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CotizacionServiceImpl implements CotizacionService {
    @Autowired
    private CotizacionRepositoryPort repositoryPort;

    @Override
    @Transactional(readOnly = true)
    public List<Cotizacion> listar() { return (List<Cotizacion>) repositoryPort.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cotizacion> porId(Long id) { return repositoryPort.findById(id); }

    @Override
    @Transactional
    public Cotizacion guardar(Cotizacion c) { return repositoryPort.guardar(c); }

    @Override
    @Transactional
    public void eliminar(Long id) { repositoryPort.deleteById(id); }
}