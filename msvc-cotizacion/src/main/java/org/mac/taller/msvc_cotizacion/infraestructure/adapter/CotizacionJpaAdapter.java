package org.mac.taller.msvc_cotizacion.infraestructure.adapter;

import org.mac.taller.msvc_cotizacion.application.port.CotizacionRepositoryPort;
import org.mac.taller.msvc_cotizacion.domain.model.AutorizacionCliente;
import org.mac.taller.msvc_cotizacion.domain.model.Cotizacion;
import org.mac.taller.msvc_cotizacion.domain.model.TareaReparacion;
import org.mac.taller.msvc_cotizacion.infraestructure.entity.TareaReparacionEntity;
import org.mac.taller.msvc_cotizacion.infraestructure.entity.AutorizacionClienteEntity;
import org.mac.taller.msvc_cotizacion.infraestructure.entity.CotizacionEntity;
import org.mac.taller.msvc_cotizacion.infraestructure.repository.CotizacionJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CotizacionJpaAdapter implements CotizacionRepositoryPort {

    private final CotizacionJpaRepository repository;

    public CotizacionJpaAdapter(CotizacionJpaRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Cotizacion> findAll() {
        return repository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Cotizacion> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Cotizacion guardar(Cotizacion cotizacion) {
        CotizacionEntity entity = toEntity(cotizacion);
        CotizacionEntity entityGuardada = repository.save(entity);
        return toDomain(entityGuardada);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    //pasar de Entidad a Dominio
    private Cotizacion toDomain(CotizacionEntity entity) {
        Cotizacion cotizacion = new Cotizacion();

        cotizacion.setId(entity.getId());
        cotizacion.setVehiculoId(entity.getVehiculoId());
        cotizacion.setEstado(entity.getEstado());
        cotizacion.setDiasInactividadEstimados(
                entity.getDiasInactividadEstimados()
        );
        cotizacion.setFechaCreacion(entity.getFechaCreacion());

        //ItemCotizacion
        List<TareaReparacion> tareas = entity.getTareas()
                .stream()
                .map(tareaEntity -> {
                    TareaReparacion tarea = new TareaReparacion();

                    tarea.setId(tareaEntity.getTareaId());
                    tarea.setDescripcion(tareaEntity.getDescripcion());
                    tarea.setCostoEstimado(tareaEntity.getCostoEstimado());

                    return tarea;
                })
                .collect(Collectors.toList());

        cotizacion.setTareas(tareas);

        //De autorizacionCliente
        if (entity.getAutorizacion() != null) {

            AutorizacionCliente autorizacion = new AutorizacionCliente();

            autorizacion.setMetodoAutorizacion(
                    entity.getAutorizacion().getMetodoAutorizacion()
            );

            autorizacion.setFechaHora(
                    entity.getAutorizacion().getFechaHora()
            );

            autorizacion.setEvidenciaUrl(
                    entity.getAutorizacion().getEvidenciaUrl()
            );

            cotizacion.setAutorizacion(autorizacion);
        }

        return cotizacion;
    }

    //pasar de Dominio a Entidad
    private CotizacionEntity toEntity(Cotizacion cotizacion) {

        CotizacionEntity entity = new CotizacionEntity();

        entity.setId(cotizacion.getId());
        entity.setVehiculoId(cotizacion.getVehiculoId());
        entity.setEstado(cotizacion.getEstado());
        entity.setTotalEstimado(cotizacion.getTotalEstimado());
        entity.setDiasInactividadEstimados(
                cotizacion.getDiasInactividadEstimados()
        );
        entity.setFechaCreacion(cotizacion.getFechaCreacion());

        //de ItemCotizacion
        List<TareaReparacionEntity> tareas = cotizacion.getTareas()
                .stream()
                .map(tarea -> {

                    TareaReparacionEntity tareaEntity =
                            new TareaReparacionEntity();

                    tareaEntity.setTareaId(tarea.getId());
                    tareaEntity.setDescripcion(tarea.getDescripcion());
                    tareaEntity.setCostoEstimado(tarea.getCostoEstimado());

                    return tareaEntity;
                })
                .collect(Collectors.toList());

        entity.setTareas(tareas);

        //de autorizacion
        if (cotizacion.getAutorizacion() != null) {

            AutorizacionClienteEntity autorizacionEntity =
                    new AutorizacionClienteEntity();

            autorizacionEntity.setMetodoAutorizacion(
                    cotizacion.getAutorizacion().getMetodoAutorizacion()
            );

            autorizacionEntity.setFechaHora(
                    cotizacion.getAutorizacion().getFechaHora()
            );

            autorizacionEntity.setEvidenciaUrl(
                    cotizacion.getAutorizacion().getEvidenciaUrl()
            );

            entity.setAutorizacion(autorizacionEntity);
        }
        return entity;
    }
}
