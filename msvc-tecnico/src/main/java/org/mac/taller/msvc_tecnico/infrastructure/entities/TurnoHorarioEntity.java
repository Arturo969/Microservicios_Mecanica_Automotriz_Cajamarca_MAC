package org.mac.taller.msvc_tecnico.infrastructure.entities;

import jakarta.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class TurnoHorarioEntity {
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public TurnoHorarioEntity() {
    }

    public TurnoHorarioEntity(LocalTime horaInicio, LocalTime horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}
