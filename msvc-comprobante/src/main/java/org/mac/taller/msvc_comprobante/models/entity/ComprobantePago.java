package org.mac.taller.msvc_comprobante.models.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name="comprobantes")
public class ComprobantePago {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long informeEntregaId;
    private BigDecimal montoBase;

    @Transient
    private static final BigDecimal TASA_IGV = new BigDecimal("0.18");

    //INVARIANTE
    public BigDecimal calcularTotalConIgv() {
        if(montoBase == null) return BigDecimal.ZERO;
        BigDecimal impuestos = montoBase.multiply(TASA_IGV).setScale(2, RoundingMode.HALF_UP);
        return montoBase.add(impuestos);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getInformeEntregaId() { return informeEntregaId; }
    public void setInformeEntregaId(Long informeEntregaId) { this.informeEntregaId = informeEntregaId; }
    public BigDecimal getMontoBase() { return montoBase; }
    public void setMontoBase(BigDecimal montoBase) { this.montoBase = montoBase; }
}