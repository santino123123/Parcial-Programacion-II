package com.mycompany.transporteturistico;

import java.time.LocalDate;

public abstract class Vehiculo {

    private String patente;
    private String marca;
    private int capacidadPasajeros;
    private int anioFabricacion;

    public Vehiculo(String patente, String marca, int capacidadPasajeros, int anioFabricacion) {
        setPatente(patente);
        setMarca(marca);
        setCapacidadPasajeros(capacidadPasajeros);
        setAnioFabricacion(anioFabricacion);
    }

    // Validaciones: son static para poder usarlas tambien desde el menu
    public static void validarPatente(String patente) {
        if (patente == null || patente.trim().isEmpty()) {
            throw new IllegalArgumentException("La patente no puede estar vacia.");
        }
    }

    public static void validarMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede estar vacia.");
        }
    }

    public static void validarCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad de pasajeros debe ser mayor a cero.");
        }
    }

    public static void validarAnio(int anio) {
        int anioActual = LocalDate.now().getYear();
        if (anio < 1990) {
            throw new IllegalArgumentException("El anio de fabricacion no puede ser menor a 1990.");
        }
        if (anio > anioActual) {
            throw new IllegalArgumentException("El anio de fabricacion no puede ser mayor al actual (" + anioActual + ").");
        }
    }

    // Getters
    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return marca;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    // Setters (final porque los llama el constructor)
    public final void setPatente(String patente) {
        validarPatente(patente);
        this.patente = patente.trim().toUpperCase();
    }

    public final void setMarca(String marca) {
        validarMarca(marca);
        this.marca = marca.trim();
    }

    public final void setCapacidadPasajeros(int capacidadPasajeros) {
        validarCapacidad(capacidadPasajeros);
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public final void setAnioFabricacion(int anioFabricacion) {
        validarAnio(anioFabricacion);
        this.anioFabricacion = anioFabricacion;
    }

    // Cada tipo de vehiculo hace su servicio de forma distinta
    public abstract void realizarServicio();

    @Override
    public String toString() {
        return "Patente: " + patente + " | Marca: " + marca
                + " | Capacidad: " + capacidadPasajeros
                + " | Anio: " + anioFabricacion;
    }
}