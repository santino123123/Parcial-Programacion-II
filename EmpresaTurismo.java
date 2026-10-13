package com.mycompany.transporteturistico;

import java.util.ArrayList;

public class EmpresaTurismo {

    private ArrayList<Vehiculo> vehiculos;

    public EmpresaTurismo() {
        vehiculos = new ArrayList<>();
    }

    public boolean estaVacia() {
        return vehiculos.isEmpty();
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void agregarVehiculo(Vehiculo v) {
        if (existePatente(v.getPatente())) {
            throw new IllegalArgumentException("Ya existe un vehiculo con la patente " + v.getPatente() + ".");
        }
        vehiculos.add(v);
    }

    // Busqueda manual recorriendo la lista
    public Vehiculo buscarPorPatente(String patente) {
        if (patente == null) {
            return null;
        }
        String buscada = patente.trim();
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getPatente().equalsIgnoreCase(buscada)) {
                return vehiculos.get(i);
            }
        }
        return null;
    }

    public boolean existePatente(String patente) {
        return buscarPorPatente(patente) != null;
    }

    // Polimorfismo: se llama realizarServicio() sin saber de que tipo es cada vehiculo
    public void realizarServicios() {
        for (Vehiculo v : vehiculos) {
            v.realizarServicio();
        }
    }

    public ArrayList<Vehiculo> filtrarPorCapacidadMayorA(int valor) {
        ArrayList<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            if (v.getCapacidadPasajeros() > valor) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    // Ordenamiento burbuja por anio de fabricacion (descendente), trabaja sobre una copia
    public ArrayList<Vehiculo> ordenarPorAnioDescendente() {
        ArrayList<Vehiculo> copia = new ArrayList<>(vehiculos);
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).getAnioFabricacion() < copia.get(j + 1).getAnioFabricacion()) {
                    Vehiculo aux = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, aux);
                }
            }
        }
        return copia;
    }

    // Ordenamiento por seleccion por capacidad (descendente), tambien sobre una copia
    public ArrayList<Vehiculo> ordenarPorCapacidadDescendente() {
        ArrayList<Vehiculo> copia = new ArrayList<>(vehiculos);
        for (int i = 0; i < copia.size() - 1; i++) {
            int posMayor = i;
            for (int j = i + 1; j < copia.size(); j++) {
                if (copia.get(j).getCapacidadPasajeros() > copia.get(posMayor).getCapacidadPasajeros()) {
                    posMayor = j;
                }
            }
            if (posMayor != i) {
                Vehiculo aux = copia.get(i);
                copia.set(i, copia.get(posMayor));
                copia.set(posMayor, aux);
            }
        }
        return copia;
    }
}