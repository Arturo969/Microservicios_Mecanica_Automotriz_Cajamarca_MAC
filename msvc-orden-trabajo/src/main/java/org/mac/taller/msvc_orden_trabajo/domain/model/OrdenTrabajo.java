package org.mac.taller.msvc_orden_trabajo.domain.model;



import java.util.ArrayList;
import java.util.List;

public class OrdenTrabajo {
    private Long id;
    private Long cotizacionId;
    private Long vehiculoId;
    private Long tecnicoAsignadoId;
    private String estado;
    private boolean cerrada;
    private List<TareaReparacion> tareas = new ArrayList<>();
    private List<OrdenBahia> historialBahias = new ArrayList<>();

    public void asignarBahia(OrdenBahia asignacion){
        historialBahias.stream()
                .filter(b -> "ACTIVA".equals(b.getEstadoAsignacion()))
                .forEach(OrdenBahia::finalizarAsignacion);
        historialBahias.add(asignacion);
    }

    public void cerrarOrden() {
        if(tareas.stream().anyMatch(t -> !t.isCompletada())){
            throw new IllegalStateException("No se puede cerrar la orden: hay tareas incompletas sin evidencia");
        }
        historialBahias.stream()
                .filter(b -> "ACTIVA".equals(b.getEstadoAsignacion()))
                .forEach(OrdenBahia::finalizarAsignacion);
        this.cerrada = true;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getCotizacionId() {return cotizacionId;}
    public void setCotizacionId(Long cotizacionId) {this.cotizacionId = cotizacionId;}
    public Long getVehiculoId() {return vehiculoId;}
    public void setVehiculoId(Long vehiculoId) {this.vehiculoId = vehiculoId;}
    public Long getTecnicoAsignadoId() {return tecnicoAsignadoId;}
    public void setTecnicoAsignadoId(Long tecnicoAsignadoId) {this.tecnicoAsignadoId = tecnicoAsignadoId;}
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
    public boolean isCerrada() {return cerrada;}
    public void setCerrada(boolean cerrada) {this.cerrada = cerrada;}

    public List<TareaReparacion> getTareas() {
        return tareas;
    }

    public void setTareas(List<TareaReparacion> tareas) {
        this.tareas = tareas;
    }

    public List<OrdenBahia> getHistorialBahias() {
        return historialBahias;
    }

    public void setHistorialBahias(List<OrdenBahia> historialBahias) {
        this.historialBahias = historialBahias;
    }
}
