package org.mac.taller.msvc_tecnico.infrastructure.adapters.inbound.rest.dto;

import java.time.LocalTime;

public class TurnoHorarioDTO {
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public TurnoHorarioDTO() {
    }

    public TurnoHorarioDTO(LocalTime horaInicio, LocalTime horaFin) {
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
