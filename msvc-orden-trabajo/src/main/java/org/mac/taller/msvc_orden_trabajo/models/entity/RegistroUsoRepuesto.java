package org.mac.taller.msvc_orden_trabajo.models.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class RegistroUsoRepuesto {
    private Long repuestoId; // ID Remoto a msvc-repuesto
    private int cantidadUtilizada;

    public Long getRepuestoId() { return repuestoId; }
    public void setRepuestoId(Long repuestoId) { this.repuestoId = repuestoId; }
    public int getCantidadUtilizada() { return cantidadUtilizada; }
    public void setCantidadUtilizada(int cantidadUtilizada) { this.cantidadUtilizada = cantidadUtilizada; }
}