package org.mac.taller.msvc_tecnico.models.entity;

import jakarta.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class TurnoHorario {
    private LocalTime horaInicio;
    private LocalTime horaFin;
    
    public boolean estaEnHorario(LocalTime momento) {
        return !momento.isBefore(horaInicio) && !momento.isAfter(horaFin);
    }
    
    // Getters/Setters
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
}