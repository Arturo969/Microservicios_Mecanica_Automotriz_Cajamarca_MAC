package org.mac.taller.msvc_vehiculo.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Kilometraje {
    private int valor;
    
    public Kilometraje() {}
    public Kilometraje(int valor) { this.valor = valor; }
    
    public int getValor() { return valor; }
    public void setValor(int valor) { this.valor = valor; }
    
    public boolean esMenorQue(Kilometraje otro) {
        return this.valor < otro.getValor();
    }
}