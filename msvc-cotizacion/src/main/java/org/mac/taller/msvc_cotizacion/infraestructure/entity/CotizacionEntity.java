package org.mac.taller.msvc_cotizacion.infraestructure.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cotizaciones")
public class CotizacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehiculoId;
    private String estado;

    private Double totalEstimado;
    private int diasInactividadEstimados;
    private LocalDateTime fechaCreacion;

    @ElementCollection
    private List<TareaReparacionEntity> tareas = new ArrayList<>();

    @Embedded
    private AutorizacionClienteEntity autorizacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotalEstimado() {
        return totalEstimado;
    }

    public void setTotalEstimado(Double totalEstimado) {
        this.totalEstimado = totalEstimado;
    }

    public int getDiasInactividadEstimados() {
        return diasInactividadEstimados;
    }

    public void setDiasInactividadEstimados(int diasInactividadEstimados) {
        this.diasInactividadEstimados = diasInactividadEstimados;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<TareaReparacionEntity> getTareas() {
        return tareas;
    }

    public void setTareas(List<TareaReparacionEntity> tareas) {
        this.tareas = tareas;
    }

    public AutorizacionClienteEntity getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(AutorizacionClienteEntity autorizacion) {
        this.autorizacion = autorizacion;
    }
}
