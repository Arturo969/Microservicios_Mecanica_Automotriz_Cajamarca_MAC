package org.mac.taller.msvc_cotizacion.models.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cotizaciones")
public class Cotizacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long vehiculoId;
    private String estado;
    
    private Double totalEstimado;
    private int diasInactividadEstimados;
    private LocalDateTime fechaCreacion;
    
    @ElementCollection
    private List<ItemCotizacion> items = new ArrayList<>();
    
    @Embedded
    private AutorizacionCliente autorizacion;
    
    public void agregarItem(ItemCotizacion item) {
        this.items.add(item);
    }
    
    public void aprobar(AutorizacionCliente autorizacion) {
        if(!"CREADA".equals(this.estado)) {
            throw new IllegalStateException("Solo cotizaciones CREADAS pueden aprobarse");
        }
        this.autorizacion = autorizacion;
        this.estado = "APROBADA";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Long vehiculoId) { this.vehiculoId = vehiculoId; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Double getTotalEstimado() { return totalEstimado; }
    public void setTotalEstimado(Double totalEstimado) { this.totalEstimado = totalEstimado; }
    public int getDiasInactividadEstimados() { return diasInactividadEstimados; }
    public void setDiasInactividadEstimados(int diasInactividadEstimados) { this.diasInactividadEstimados = diasInactividadEstimados; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public List<ItemCotizacion> getItems() { return items; }
    public void setItems(List<ItemCotizacion> items) { this.items = items; }
    public AutorizacionCliente getAutorizacion() { return autorizacion; }
    public void setAutorizacion(AutorizacionCliente autorizacion) { this.autorizacion = autorizacion; }
}