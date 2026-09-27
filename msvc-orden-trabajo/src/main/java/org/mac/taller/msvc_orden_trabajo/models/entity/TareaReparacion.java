package org.mac.taller.msvc_orden_trabajo.models.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tareas")
public class TareaReparacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private boolean completada;

    @ElementCollection
    private List<FotoEvidencia> evidencias = new ArrayList<>();

    @ElementCollection
    private List<RegistroUsoRepuesto> repuestosUsados = new ArrayList<>();

    public void agregarEvidencia(FotoEvidencia foto) { this.evidencias.add(foto); }
    public void registrarRepuesto(RegistroUsoRepuesto repuesto) { this.repuestosUsados.add(repuesto); }
    
    public void marcarCompletada() {
        if(evidencias.isEmpty()) throw new IllegalStateException("Se requiere foto de evidencia para completar la tarea.");
        this.completada = true;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }
    public List<FotoEvidencia> getEvidencias() { return evidencias; }
    public void setEvidencias(List<FotoEvidencia> evidencias) { this.evidencias = evidencias; }
    public List<RegistroUsoRepuesto> getRepuestosUsados() { return repuestosUsados; }
    public void setRepuestosUsados(List<RegistroUsoRepuesto> repuestosUsados) { this.repuestosUsados = repuestosUsados; }
}