package org.mac.taller.msvc_cotizacion.models.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class ItemCotizacion {
    private String descripcion;
    private Double precioUnitario;
    private int cantidad;
    
    // Getters/Setters
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}