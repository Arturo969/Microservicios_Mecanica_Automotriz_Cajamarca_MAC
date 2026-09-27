package org.mac.taller.msvc_tecnico.models.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tecnicos")
public class Tecnico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String dni;
    private String nombreCompleto;
    
    @ElementCollection
    private List<String> especialidades = new ArrayList<>();
    
    private String estado;
    
    @Embedded
    private TurnoHorario turnoLaboral;
    
    public void asignarAOrden() {
        if(!"DISPONIBLE".equals(this.estado)) {
            throw new IllegalStateException("El técnico no está disponible");
        }
        this.estado = "OCUPADO";
    }
    
    public void liberarTecnico() {
        this.estado = "DISPONIBLE";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public List<String> getEspecialidades() { return especialidades; }
    public void setEspecialidades(List<String> especialidades) { this.especialidades = especialidades; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public TurnoHorario getTurnoLaboral() { return turnoLaboral; }
    public void setTurnoLaboral(TurnoHorario turnoLaboral) { this.turnoLaboral = turnoLaboral; }
}