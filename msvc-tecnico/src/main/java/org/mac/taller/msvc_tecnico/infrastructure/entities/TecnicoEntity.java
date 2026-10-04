package org.mac.taller.msvc_tecnico.infrastructure.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tecnicos")
public class TecnicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String nombreCompleto;

    @ElementCollection
    private List<String> especialidades = new ArrayList<>();

    private String estado;

    @Embedded
    private TurnoHorarioEntity turnoLaboral;

    public TecnicoEntity() {
    }

    public TecnicoEntity(Long id, String dni, String nombreCompleto, List<String> especialidades, String estado, TurnoHorarioEntity turnoLaboral) {
        this.id = id;
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        if (especialidades != null) {
            this.especialidades = especialidades;
        }
        this.estado = estado;
        this.turnoLaboral = turnoLaboral;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TurnoHorarioEntity getTurnoLaboral() {
        return turnoLaboral;
    }

    public void setTurnoLaboral(TurnoHorarioEntity turnoLaboral) {
        this.turnoLaboral = turnoLaboral;
    }
}
