package org.mac.taller.msvc_tecnico.domain.model;

import java.time.LocalTime;

public class TurnoHorario {
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public TurnoHorario() {
    }

    public TurnoHorario(LocalTime horaInicio, LocalTime horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public boolean estaEnHorario(LocalTime momento) {
        if (momento == null || horaInicio == null || horaFin == null) {
            return false;
        }
        return !momento.isBefore(horaInicio) && !momento.isAfter(horaFin);
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
