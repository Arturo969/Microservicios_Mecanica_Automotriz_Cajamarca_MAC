package org.mac.taller.msvc_orden_trabajo.models.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ordenes_bahias")
public class OrdenBahia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long bahiaId; // ID Remoto
    
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;
    private String estadoAsignacion; // ACTIVA, HISTORICA

    public OrdenBahia() {}
    public OrdenBahia(Long bahiaId) {
        this.bahiaId = bahiaId;
        this.fechaIngreso = LocalDateTime.now();
        this.estadoAsignacion = "ACTIVA";
    }
    public void finalizarAsignacion() {
        this.fechaSalida = LocalDateTime.now();
        this.estadoAsignacion = "HISTORICA";
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getBahiaId() { return bahiaId; }
    public void setBahiaId(Long bahiaId) { this.bahiaId = bahiaId; }
    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDateTime fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }
    public String getEstadoAsignacion() { return estadoAsignacion; }
    public void setEstadoAsignacion(String estadoAsignacion) { this.estadoAsignacion = estadoAsignacion; }
}