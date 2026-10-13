package com.mycompany.transporteturistico;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static EmpresaTurismo empresa = new EmpresaTurismo();

    public static void main(String[] args) {
        System.out.print("Cargar los datos de prueba? (S/N): ");
        String respuesta = sc.nextLine().trim();
        if (respuesta.equalsIgnoreCase("s")) {
            cargarDatosDePrueba();
            System.out.println("Datos de prueba cargados.");
        }

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elija una opcion: ");
            System.out.println();

            switch (opcion) {
                case 1:
                    agregarVehiculo();
                    break;
                case 2:
                    mostrarLista(empresa.getVehiculos(), "No hay vehiculos cargados.");
                    break;
                case 3:
                    realizarServicios();
                    break;
                case 4:
                    buscarVehiculo();
                    break;
                case 5:
                    mostrarPorCapacidad();
                    break;
                case 6:
                    mostrarLista(empresa.ordenarPorAnioDescendente(), "No hay vehiculos cargados.");
                    break;
                case 7:
                    mostrarLista(empresa.ordenarPorCapacidadDescendente(), "No hay vehiculos cargados.");
                    break;
                case 8:
                    System.out.println("Saliendo del sistema. Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion invalida. Elija un numero del 1 al 8.");
            }
        } while (opcion != 8);
    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println("===== TRANSPORTE TURISTICO =====");
        System.out.println("1. Agregar vehiculo");
        System.out.println("2. Mostrar todos los vehiculos");
        System.out.println("3. Realizar servicios turisticos");
        System.out.println("4. Buscar vehiculo por patente");
        System.out.println("5. Mostrar vehiculos con capacidad mayor a un valor");
        System.out.println("6. Mostrar vehiculos ordenados por anio de fabricacion (descendente)");
        System.out.println("7. Mostrar vehiculos ordenados por capacidad de pasajeros (descendente)");
        System.out.println("8. Salir");
    }

    // ---------- Lectura de datos ----------

    // Pide un entero y repite hasta que se ingrese un numero valido
    static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un numero entero. Intente de nuevo.");
            }
        }
    }

    static String pedirPatente() {
        while (true) {
            System.out.print("Patente: ");
            String patente = sc.nextLine();
            try {
                Vehiculo.validarPatente(patente);
                if (empresa.existePatente(patente)) {
                    System.out.println("Error: ya existe un vehiculo con esa patente.");
                } else {
                    return patente.trim().toUpperCase();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static String pedirMarca() {
        while (true) {
            System.out.print("Marca: ");
            String marca = sc.nextLine();
            try {
                Vehiculo.validarMarca(marca);
                return marca.trim();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static int pedirCapacidad() {
        while (true) {
            int capacidad = leerEntero("Capacidad de pasajeros: ");
            try {
                Vehiculo.validarCapacidad(capacidad);
                return capacidad;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static int pedirAnio() {
        while (true) {
            int anio = leerEntero("Anio de fabricacion: ");
            try {
                Vehiculo.validarAnio(anio);
                return anio;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static int pedirPisos() {
        while (true) {
            int pisos = leerEntero("Cantidad de pisos (1 o 2): ");
            try {
                ColectivoTuristico.validarPisos(pisos);
                return pisos;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static boolean pedirAire() {
        while (true) {
            System.out.print("Tiene aire acondicionado? (S/N): ");
            String respuesta = sc.nextLine().trim();
            if (respuesta.equalsIgnoreCase("s")) {
                return true;
            }
            if (respuesta.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Error: ingrese S o N.");
        }
    }

    static String pedirMotor() {
        while (true) {
            System.out.print("Tipo de motor (NAFTA, DIESEL o ELECTRICO): ");
            String motor = sc.nextLine();
            try {
                LanchaTuristica.validarMotor(motor);
                return motor.trim().toUpperCase();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------- Opciones del menu ----------

    static void agregarVehiculo() {
        System.out.println("--- Agregar vehiculo ---");
        System.out.println("1. Colectivo turistico");
        System.out.println("2. Van ejecutiva");
        System.out.println("3. Lancha turistica");

        int tipo;
        do {
            tipo = leerEntero("Tipo de vehiculo: ");
            if (tipo < 1 || tipo > 3) {
                System.out.println("Error: el tipo debe ser 1, 2 o 3.");
            }
        } while (tipo < 1 || tipo > 3);

        String patente = pedirPatente();
        String marca = pedirMarca();
        int capacidad = pedirCapacidad();
        int anio = pedirAnio();

        Vehiculo nuevo;
        if (tipo == 1) {
            int pisos = pedirPisos();
            nuevo = new ColectivoTuristico(patente, marca, capacidad, anio, pisos);
        } else if (tipo == 2) {
            boolean aire = pedirAire();
            nuevo = new VanEjecutiva(patente, marca, capacidad, anio, aire);
        } else {
            String motor = pedirMotor();
            nuevo = new LanchaTuristica(patente, marca, capacidad, anio, motor);
        }

        empresa.agregarVehiculo(nuevo);
        System.out.println("Vehiculo agregado correctamente.");
    }

    static void realizarServicios() {
        if (empresa.estaVacia()) {
            System.out.println("No hay vehiculos cargados para realizar servicios.");
        } else {
            System.out.println("--- Servicios turisticos ---");
            empresa.realizarServicios();
        }
    }

    static void buscarVehiculo() {
        System.out.println("--- Buscar vehiculo por patente ---");
        String patente;
        while (true) {
            System.out.print("Patente a buscar: ");
            patente = sc.nextLine();
            try {
                Vehiculo.validarPatente(patente);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        Vehiculo encontrado = empresa.buscarPorPatente(patente);
        if (encontrado == null) {
            System.out.println("No se encontro ningun vehiculo con la patente " + patente.trim().toUpperCase() + ".");
        } else {
            System.out.println("Vehiculo encontrado:");
            System.out.println(encontrado);
        }
    }

    static void mostrarPorCapacidad() {
        int valor = leerEntero("Mostrar vehiculos con capacidad mayor a: ");
        ArrayList<Vehiculo> filtrados = empresa.filtrarPorCapacidadMayorA(valor);
        mostrarLista(filtrados, "No hay vehiculos con capacidad mayor a " + valor + ".");
    }

    static void mostrarLista(ArrayList<Vehiculo> lista, String mensajeVacio) {
        if (lista.isEmpty()) {
            System.out.println(mensajeVacio);
        } else {
            for (Vehiculo v : lista) {
                System.out.println(v);
            }
        }
    }

    // Datos de la tabla del enunciado
    static void cargarDatosDePrueba() {
        empresa.agregarVehiculo(new ColectivoTuristico("AA123BB", "Mercedes Benz", 45, 2020, 2));
        empresa.agregarVehiculo(new ColectivoTuristico("AB456CD", "Scania", 50, 2022, 1));
        empresa.agregarVehiculo(new VanEjecutiva("AC789EF", "Renault", 15, 2021, true));
        empresa.agregarVehiculo(new VanEjecutiva("AD321GH", "Toyota", 12, 2019, false));
        empresa.agregarVehiculo(new LanchaTuristica("LAN001", "Yamaha", 8, 2023, "NAFTA"));
        empresa.agregarVehiculo(new LanchaTuristica("LAN002", "Mercury", 10, 2022, "ELECTRICO"));
    }
}