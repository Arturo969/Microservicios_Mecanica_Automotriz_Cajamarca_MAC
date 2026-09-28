package org.mac.taller.msvc_bahia.domain.model;

public class BahiaTrabajo {
    private Long id;
    private String codigo;
    private String estado; // DISPONIBLE, OCUPADA, MANTENIMIENTO
    private String tipo;   // ELEVADOR, PINTURA, DIAGNOSTICO

    public void ocupar() {
        if (!"DISPONIBLE".equals(this.estado)) throw new IllegalStateException("La bahía no está disponible");
        this.estado = "OCUPADA";
    }

    public void liberar() {
        this.estado = "DISPONIBLE";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}