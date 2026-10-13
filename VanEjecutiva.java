package com.mycompany.transporteturistico;

public class VanEjecutiva extends Vehiculo {

    private boolean aireAcondicionado;

    public VanEjecutiva(String patente, String marca, int capacidadPasajeros,
                        int anioFabricacion, boolean aireAcondicionado) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    @Override
    public void realizarServicio() {
        String aire;
        if (aireAcondicionado) {
            aire = "con aire acondicionado";
        } else {
            aire = "sin aire acondicionado";
        }
        System.out.println("Van " + getPatente() + ": realizando traslado ejecutivo " + aire
                + " para hasta " + getCapacidadPasajeros() + " pasajeros.");
    }

    @Override
    public String toString() {
        String aire;
        if (aireAcondicionado) {
            aire = "Si";
        } else {
            aire = "No";
        }
        return "[Van] " + super.toString() + " | Aire: " + aire;
    }
}