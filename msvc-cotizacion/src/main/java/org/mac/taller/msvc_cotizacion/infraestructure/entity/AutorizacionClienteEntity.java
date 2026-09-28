package org.mac.taller.msvc_cotizacion.infraestructure.entity;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class AutorizacionClienteEntity {

    private String metodoAutorizacion;
    private LocalDateTime fechaHora;
    private String evidenciaUrl;

    public String getMetodoAutorizacion() {
        return metodoAutorizacion;
    }

    public void setMetodoAutorizacion(String metodoAutorizacion) {
        this.metodoAutorizacion = metodoAutorizacion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEvidenciaUrl() {
        return evidenciaUrl;
    }

    public void setEvidenciaUrl(String evidenciaUrl) {
        this.evidenciaUrl = evidenciaUrl;
    }
}