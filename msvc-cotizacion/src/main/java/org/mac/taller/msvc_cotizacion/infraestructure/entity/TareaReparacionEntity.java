package org.mac.taller.msvc_cotizacion.infraestructure.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class TareaReparacionEntity {

    private Long tareaId;
    private String descripcion;
    private Double costoEstimado;

    public Long getTareaId() {
        return tareaId;
    }

    public void setTareaId(Long tareaId) {
        this.tareaId = tareaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCostoEstimado() {
        return costoEstimado;
    }

    public void setCostoEstimado(Double costoEstimado) {
        this.costoEstimado = costoEstimado;
    }
}