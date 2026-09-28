package org.mac.taller.msvc_orden_trabajo.infrastructure.adapters;

import org.mac.taller.msvc_orden_trabajo.domain.model.*;
import org.mac.taller.msvc_orden_trabajo.infrastructure.entities.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdenTrabajoMapper {
    // DE ENTITY (JPA) HACIA DOMINIO (PURO)
    public OrdenTrabajo toDomain(OrdenTrabajoEntity entity){
        if (entity == null) return null;
        OrdenTrabajo dominio = new OrdenTrabajo();
        dominio.setId(entity.getId());
        dominio.setCotizacionId(entity.getCotizacionId());
        dominio.setVehiculoId(entity.getVehiculoId());
        dominio.setTecnicoAsignadoId(entity.getTecnicoAsignadoId());
        dominio.setEstado(entity.getEstado());
        dominio.setCerrada(entity.isCerrada());

        // Mapear Tareas
        if(entity.getTareas() != null){
            List<TareaReparacion> tareasDomain = entity.getTareas().stream().map(tareaEntity ->{
                TareaReparacion tarea = new TareaReparacion();
                tarea.setId(tareaEntity.getId());
                tarea.setDescripcion(tareaEntity.getDescripcion());
                tarea.setCompletada(tareaEntity.isCompletada());
                //Mapear Evidencias
                if(tareaEntity.getEvidencias() != null){
                    tarea.setEvidencias(tareaEntity.getEvidencias().stream().map(evidenciaEntity -> {
                        FotoEvidencia foto = new FotoEvidencia();
                        foto.setUrlFoto(evidenciaEntity.getUrlFoto());
                        foto.setFechaCaptura(evidenciaEntity.getFechaCaptura());
                        return foto;
                    } ).collect(Collectors.toList()));
                }

                //Mapear Repuestos
                if(tareaEntity.getRepuestosUsados() != null){
                    tarea.setRepuestosUsados(tareaEntity.getRepuestosUsados().stream().map(repuestoEntity -> {
                        RegistroUsoRepuesto repuesto = new RegistroUsoRepuesto();
                        repuesto.setRepuestoId(repuestoEntity.getRepuestoId());
                        repuesto.setCantidadUtilizada(repuestoEntity.getCantidadUtilizada());
                        return repuesto;
                    }).collect(Collectors.toList()));
                }
                return tarea;
            }).collect(Collectors.toList());
            dominio.setTareas(tareasDomain);
        }

        // Mapear Bahías
        if(entity.getHistorialBahias() != null){
            List<OrdenBahia> bahiasDomain = entity.getHistorialBahias().stream().map(bahiaEntity -> {
                OrdenBahia bahia = new OrdenBahia();
                bahia.setId(bahiaEntity.getId());
                bahia.setBahiaId(bahiaEntity.getBahiaId());
                bahia.setFechaIngreso(bahiaEntity.getFechaIngreso());
                bahia.setFechaSalida(bahiaEntity.getFechaSalida());
                bahia.setEstadoAsignacion(bahiaEntity.getEstadoAsignacion());
                return bahia;
            }).collect(Collectors.toList());
            dominio.setHistorialBahias(bahiasDomain);
        }
        return dominio;
    }

    // DE DOMINIO HACIA ENTITY (JPA)
    public OrdenTrabajoEntity toEntity(OrdenTrabajo dominio) {
        if (dominio == null) return null;
        OrdenTrabajoEntity entity = new OrdenTrabajoEntity();
        entity.setId(dominio.getId());
        entity.setCotizacionId(dominio.getCotizacionId());
        entity.setVehiculoId(dominio.getVehiculoId());
        entity.setTecnicoAsignadoId(dominio.getTecnicoAsignadoId());
        entity.setEstado(dominio.getEstado());
        entity.setCerrada(dominio.isCerrada());

        if (dominio.getTareas() != null) {
            List<TareaReparacionEntity> tareasEntity = dominio.getTareas().stream().map(tareaDomain -> {
                TareaReparacionEntity tarea = new TareaReparacionEntity();
                tarea.setId(tareaDomain.getId());
                tarea.setDescripcion(tareaDomain.getDescripcion());
                tarea.setCompletada(tareaDomain.isCompletada());

                if(tareaDomain.getEvidencias() != null) {
                    tarea.setEvidencias(tareaDomain.getEvidencias().stream().map(evidenciaDomain -> {
                        FotoEvidenciaEntity foto = new FotoEvidenciaEntity();
                        foto.setUrlFoto(evidenciaDomain.getUrlFoto());
                        foto.setFechaCaptura(evidenciaDomain.getFechaCaptura());
                        return foto;
                    }).collect(Collectors.toList()));
                }

                if(tareaDomain.getRepuestosUsados() != null) {
                    tarea.setRepuestosUsados(tareaDomain.getRepuestosUsados().stream().map(repuestoDomain -> {
                        RegistroUsoRepuestoEntity repuesto = new RegistroUsoRepuestoEntity();
                        repuesto.setRepuestoId(repuestoDomain.getRepuestoId());
                        repuesto.setCantidadUtilizada(repuestoDomain.getCantidadUtilizada());
                        return repuesto;
                    }).collect(Collectors.toList()));
                }
                return tarea;
            }).collect(Collectors.toList());
            entity.setTareas(tareasEntity);
        }

        if (dominio.getHistorialBahias() != null) {
            List<OrdenBahiaEntity> bahiasEntity = dominio.getHistorialBahias().stream().map(bahiaDomain -> {
                OrdenBahiaEntity bahia = new OrdenBahiaEntity();
                bahia.setId(bahiaDomain.getId());
                bahia.setBahiaId(bahiaDomain.getBahiaId());
                bahia.setFechaIngreso(bahiaDomain.getFechaIngreso());
                bahia.setFechaSalida(bahiaDomain.getFechaSalida());
                bahia.setEstadoAsignacion(bahiaDomain.getEstadoAsignacion());
                return bahia;
            }).collect(Collectors.toList());
            entity.setHistorialBahias(bahiasEntity);
        }

        return entity;
    }

}
