package org.mac.taller.msvc_vehiculo.domain.model;

public class Vehiculo {

    private Long id;
    private String placa;
    private String vin;
    private String marca;
    private String modelo;
    private Integer kilometrajeActual;
    private Long clienteId;

    public Vehiculo() {}

    public Vehiculo(Long id, String placa, String vin, String marca, String modelo, Integer kilometrajeActual, Long clienteId) {
        this.id = id;
        this.placa = placa;
        this.vin = vin;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometrajeActual = kilometrajeActual;
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getKilometrajeActual() {
        return kilometrajeActual;
    }

    public void setKilometrajeActual(Integer kilometrajeActual) {
        this.kilometrajeActual = kilometrajeActual;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
