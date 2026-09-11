package org.mac.taller.msvc_entrega.models.entity;
import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class CondicionGarantia {
    private LocalDate fechaFinCobertura;
    public CondicionGarantia() {}
    public CondicionGarantia(LocalDate fin) { this.fechaFinCobertura = fin; }
    public LocalDate getFechaFinCobertura() { return fechaFinCobertura; }
    public void setFechaFinCobertura(LocalDate f) { this.fechaFinCobertura = f; }
}