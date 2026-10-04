package org.mac.taller.msvc_vehiculo.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name="vehiculos")
public class VehiculoEntity {

    public VehiculoEntity(Long id, String placa, String vin, String marca, String modelo, Integer kilometrajeActual, Long clienteId) {
        this.id = id;
        this.placa = placa;
        this.vin = vin;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometrajeActual = kilometrajeActual;
        this.clienteId = clienteId;
    }

    public VehiculoEntity() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="placa_valor", unique=true)
    private String placa;
    
    private String vin;
    private String marca;
    private String modelo;
    
    @Column(name="kilometraje_valor")
    private Integer kilometrajeActual;
    
    private Long clienteId;
    
    public void actualizarKilometraje(Integer nuevoKm) {
        if(this.kilometrajeActual != null && nuevoKm < this.kilometrajeActual) {
            throw new IllegalStateException("El nuevo kilometraje no puede ser menor al actual");
        }
        this.kilometrajeActual = nuevoKm;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Integer getKilometrajeActual() { return kilometrajeActual; }
    public void setKilometrajeActual(Integer kilometrajeActual) { this.kilometrajeActual = kilometrajeActual; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}