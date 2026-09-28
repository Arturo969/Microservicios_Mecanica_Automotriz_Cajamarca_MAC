package org.mac.taller.msvc_vehiculo.infrastructure.entity;

import jakarta.persistence.*;
import org.mac.taller.msvc_vehiculo.domain.model.Kilometraje;
import org.mac.taller.msvc_vehiculo.domain.model.Placa;

@Entity
@Table(name="vehiculos")
public class VehiculoEntity {

    public VehiculoEntity(Long id, Placa placa, String vin, String marca, String modelo, Kilometraje kilometrajeActual, Long clienteId) {
        this.id = id;
        this.placa = placa;
        this.vin = vin;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometrajeActual = kilometrajeActual;
        this.clienteId = clienteId;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="valor", column=@Column(name="placa_valor"))
    })
    private Placa placa;
    
    private String vin;
    private String marca;
    private String modelo;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="valor", column=@Column(name="kilometraje_valor"))
    })
    private Kilometraje kilometrajeActual;
    
    private Long clienteId;
    
    public void actualizarKilometraje(Kilometraje nuevoKm) {
        if(this.kilometrajeActual != null && nuevoKm.esMenorQue(this.kilometrajeActual)) {
            throw new IllegalStateException("El nuevo kilometraje no puede ser menor al actual");
        }
        this.kilometrajeActual = nuevoKm;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Placa getPlaca() { return placa; }
    public void setPlaca(Placa placa) { this.placa = placa; }
    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Kilometraje getKilometrajeActual() { return kilometrajeActual; }
    public void setKilometrajeActual(Kilometraje kilometrajeActual) { this.kilometrajeActual = kilometrajeActual; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}