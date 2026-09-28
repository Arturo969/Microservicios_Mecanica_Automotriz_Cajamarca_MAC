package org.mac.taller.msvc_bahia.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bahias")
public class BahiaTrabajoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String estado; // DISPONIBLE, OCUPADA, MANTENIMIENTO
    private String tipo;   // ELEVADOR, PINTURA, DIAGNOSTICO

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}