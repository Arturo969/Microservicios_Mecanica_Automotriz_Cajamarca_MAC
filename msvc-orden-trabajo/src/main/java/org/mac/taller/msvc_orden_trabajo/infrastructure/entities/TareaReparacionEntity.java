package org.mac.taller.msvc_orden_trabajo.infrastructure.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tareas")
public class TareaReparacionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private boolean completada;

    @ElementCollection
    private List<FotoEvidenciaEntity> evidencias = new ArrayList<>();

    @ElementCollection
    private List<RegistroUsoRepuestoEntity> repuestosUsados = new ArrayList<>();

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }
    public List<FotoEvidenciaEntity> getEvidencias() { return evidencias; }
    public void setEvidencias(List<FotoEvidenciaEntity> evidencias) { this.evidencias = evidencias; }
    public List<RegistroUsoRepuestoEntity> getRepuestosUsados() { return repuestosUsados; }
    public void setRepuestosUsados(List<RegistroUsoRepuestoEntity> repuestosUsados) { this.repuestosUsados = repuestosUsados; }
}