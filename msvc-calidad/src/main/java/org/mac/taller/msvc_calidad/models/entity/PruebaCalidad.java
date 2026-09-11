package org.mac.taller.msvc_calidad.models.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pruebas_calidad")
public class PruebaCalidad {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ordenTrabajoId;

    @ElementCollection
    private List<CheckSeguridad> checks = new ArrayList<>();

    // --- INVARIANTE LOGICO ---
    public boolean emitirDictamen() {
        if (checks.isEmpty()) return false;
        return checks.stream().allMatch(CheckSeguridad::isAprobado);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getOrdenTrabajoId() { return ordenTrabajoId; }
    public void setOrdenTrabajoId(Long ordenTrabajoId) { this.ordenTrabajoId = ordenTrabajoId; }
    public List<CheckSeguridad> getChecks() { return checks; }
    public void setChecks(List<CheckSeguridad> checks) { this.checks = checks; }
}