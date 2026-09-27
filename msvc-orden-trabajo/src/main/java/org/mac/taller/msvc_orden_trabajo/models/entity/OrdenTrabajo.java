package org.mac.taller.msvc_orden_trabajo.models.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ordenes_trabajo")
public class OrdenTrabajo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cotizacionId; // ID remoto
    private Long vehiculoId; // ID remoto a msvc-vehiculo
    private Long tecnicoAsignadoId; // ID remoto a msvc-tecnico
    private String estado; // e.g., EN_PROGRESO, LISTA_PARA_CALIDAD
    private boolean cerrada;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="orden_id")
    private List<TareaReparacion> tareas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="orden_id")
    private List<OrdenBahia> historialBahias = new ArrayList<>();

    // --- INVARIANTES ---
    public void asignarBahia(OrdenBahia asignacion) {
        historialBahias.stream()
            .filter(b -> "ACTIVA".equals(b.getEstadoAsignacion()))
            .forEach(OrdenBahia::finalizarAsignacion);
        historialBahias.add(asignacion);
    }

    public void cerrarOrden() {
        if (tareas.stream().anyMatch(t -> !t.isCompletada())) {
            throw new IllegalStateException("No se puede cerrar la orden: hay tareas incompletas sin evidencia");
        }
        historialBahias.stream()
            .filter(b -> "ACTIVA".equals(b.getEstadoAsignacion()))
            .forEach(OrdenBahia::finalizarAsignacion);
        this.cerrada = true;
    }
    
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
    public List<TareaReparacion> getTareas() { return tareas; }
    public void setTareas(List<TareaReparacion> tareas) { this.tareas = tareas; }
    public List<OrdenBahia> getHistorialBahias() { return historialBahias; }
    public void setHistorialBahias(List<OrdenBahia> historialBahias) { this.historialBahias = historialBahias; }
}