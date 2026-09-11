package org.mac.taller.msvc_entrega.models.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="informes_entrega")
public class InformeEntrega {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long ordenTrabajoId;
    private LocalDate fechaEntrega;

    @Embedded
    private CondicionGarantia garantia;

    @PrePersist
    public void inicializar() {
        if (this.ordenTrabajoId == null) {
            throw new IllegalArgumentException("El ID de la orden es obligatorio.");
        }
        this.fechaEntrega = LocalDate.now();
        this.garantia = new CondicionGarantia(this.fechaEntrega.plusMonths(3));
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getOrdenTrabajoId() { return ordenTrabajoId; }
    public void setOrdenTrabajoId(Long ordenTrabajoId) { this.ordenTrabajoId = ordenTrabajoId; }
    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public CondicionGarantia getGarantia() { return garantia; }
    public void setGarantia(CondicionGarantia garantia) { this.garantia = garantia; }
}