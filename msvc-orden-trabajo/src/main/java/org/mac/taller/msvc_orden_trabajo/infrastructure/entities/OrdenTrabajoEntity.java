package org.mac.taller.msvc_orden_trabajo.infrastructure.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ordenes_trabajo")
public class OrdenTrabajoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cotizacionId; // ID remoto
    private Long vehiculoId; // ID remoto a msvc-vehiculo
    private Long tecnicoAsignadoId; // ID remoto a msvc-tecnico
    private String estado; // e.g., EN_PROGRESO, LISTA_PARA_CALIDAD
    private boolean cerrada;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="orden_id")
    private List<TareaReparacionEntity> tareas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="orden_id")
    private List<OrdenBahiaEntity> historialBahias = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Long vehiculoId) { this.vehiculoId = vehiculoId; }
    public Long getTecnicoAsignadoId() { return tecnicoAsignadoId; }
    public void setTecnicoAsignadoId(Long tecnicoAsignadoId) { this.tecnicoAsignadoId = tecnicoAsignadoId; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Long getCotizacionId() { return cotizacionId; }
    public void setCotizacionId(Long cotizacionId) { this.cotizacionId = cotizacionId; }
    public boolean isCerrada() { return cerrada; }
    public void setCerrada(boolean cerrada) { this.cerrada = cerrada; }
    public List<TareaReparacionEntity> getTareas() { return tareas; }
    public void setTareas(List<TareaReparacionEntity> tareas) { this.tareas = tareas; }
    public List<OrdenBahiaEntity> getHistorialBahias() { return historialBahias; }
    public void setHistorialBahias(List<OrdenBahiaEntity> historialBahias) { this.historialBahias = historialBahias; }
}