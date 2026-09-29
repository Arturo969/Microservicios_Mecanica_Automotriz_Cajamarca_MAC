package org.mac.taller.msvc_tecnico.infrastructure.adapters.inbound.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class TecnicoRequestDTO {
    private String dni;
    private String nombreCompleto;
    private List<String> especialidades = new ArrayList<>();
    private String estado;
    private TurnoHorarioDTO turnoLaboral;

    public TecnicoRequestDTO() {
    }

    public TecnicoRequestDTO(String dni, String nombreCompleto, List<String> especialidades, String estado, TurnoHorarioDTO turnoLaboral) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        if (especialidades != null) {
            this.especialidades = especialidades;
        }
        this.estado = estado;
        this.turnoLaboral = turnoLaboral;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TurnoHorarioDTO getTurnoLaboral() {
        return turnoLaboral;
    }

    public void setTurnoLaboral(TurnoHorarioDTO turnoLaboral) {
        this.turnoLaboral = turnoLaboral;
    }
}
