package org.mac.taller.msvc_vehiculo.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Placa {
    private String valor;
    
    public Placa() {}
    public Placa(String valor) { this.valor = valor; }
    
    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }
}