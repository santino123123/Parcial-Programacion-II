package com.mycompany.transporteturistico;

public class LanchaTuristica extends Vehiculo {

    private String tipoMotor;

    public LanchaTuristica(String patente, String marca, int capacidadPasajeros,
                           int anioFabricacion, String tipoMotor) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        setTipoMotor(tipoMotor);
    }

    public static void validarMotor(String motor) {
        if (motor == null) {
            throw new IllegalArgumentException("El tipo de motor no puede ser nulo.");
        }
        String m = motor.trim().toUpperCase();
        if (!m.equals("NAFTA") && !m.equals("DIESEL") && !m.equals("ELECTRICO")) {
            throw new IllegalArgumentException("Tipo de motor invalido. Debe ser NAFTA, DIESEL o ELECTRICO.");
        }
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public final void setTipoMotor(String tipoMotor) {
        validarMotor(tipoMotor);
        this.tipoMotor = tipoMotor.trim().toUpperCase();
    }

    @Override
    public void realizarServicio() {
        System.out.println("Lancha " + getPatente() + ": realizando paseo por el rio con motor "
                + tipoMotor + " y hasta " + getCapacidadPasajeros() + " pasajeros.");
    }

    @Override
    public String toString() {
        return "[Lancha] " + super.toString() + " | Motor: " + tipoMotor;
    }
}