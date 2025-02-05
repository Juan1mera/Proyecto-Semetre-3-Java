package modelo;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSistema de Gestión de Aeropuertos");
            System.out.println("1. Agregar Aeropuerto");
            System.out.println("2. Listar Aeropuertos");
            System.out.println("3. Agregar Vuelo");
            System.out.println("4. Agregar Pasajero a Vuelo");
            System.out.println("5. Ver Vuelos por Aeropuerto");
            System.out.println("6. Ver Detalles de un Vuelo");
            System.out.println("7. Buscar Pasajero");
            System.out.println("8. Asignar Piloto a Vuelo");
            System.out.println("9. Agregar Equipaje a Pasajero");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el nombre del aeropuerto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la ubicación: ");
                    String ubicacion = scanner.nextLine();
                    System.out.print("Ingrese la capacidad: ");
                    int capacidad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    Aeropuerto aeropuerto = new Aeropuerto(nombre, ubicacion, capacidad);
                    sistema.agregarAeropuerto(aeropuerto);
                    System.out.println("Aeropuerto agregado con éxito.");
                }
                case 2 -> {
                    System.out.println("\nAeropuertos registrados:");
                    for (Aeropuerto a : sistema.getListaAeropuertos()) {
                        System.out.println(a);
                    }
                }
                case 3 -> {
                    System.out.print("Ingrese el nombre del aeropuerto: ");
                    String nombreAeropuerto = scanner.nextLine();
                    Aeropuerto aeropuerto = sistema.buscarAeropuerto(nombreAeropuerto);
                    if (aeropuerto != null) {
                        System.out.print("Ingrese el número del vuelo: ");
                        String numeroVuelo = scanner.nextLine();
                        System.out.print("Ingrese el origen del vuelo: ");
                        String origen = scanner.nextLine();
                        System.out.print("Ingrese el destino del vuelo: ");
                        String destino = scanner.nextLine();
                        System.out.print("Ingrese la hora de salida: ");
                        String horaSalida = scanner.nextLine();
                        System.out.print("Ingrese la hora de llegada: ");
                        String horaLlegada = scanner.nextLine();
                        System.out.print("Ingrese la capacidad máxima del vuelo: ");
                        int capacidadMaxima = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        Vuelo vuelo = new Vuelo(numeroVuelo, origen, destino, horaSalida, horaLlegada, capacidadMaxima);
                        sistema.agregarVuelo(nombreAeropuerto, vuelo);
                        System.out.println("Vuelo agregado con éxito.");
                    } else {
                        System.out.println("Aeropuerto no encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("Ingrese el número del vuelo: ");
                    String numeroVuelo = scanner.nextLine();
                    System.out.print("Ingrese el nombre del pasajero: ");
                    String nombrePasajero = scanner.nextLine();
                    System.out.print("Ingrese el documento del pasajero: ");
                    String documentoPasajero = scanner.nextLine();
                    System.out.print("Ingresa el pasaporte del pasajero: ");
                    String pasaportePasajero = scanner.nextLine();
                    System.out.print("Ingresa la edad del pasajero: ");
                    String edadPasajero = scanner.nextLine();
                    System.out.print("Ingresa en que calse viaja el pasajero(Economico/Intercambio/Turismo): ");
                    String viajePasajero = scanner.nextLine();

                    Pasajero pasajero = new Pasajero(nombrePasajero, documentoPasajero, pasaportePasajero, edadPasajero, viajePasajero, numeroVuelo);
                    sistema.agregarPasajero(numeroVuelo, pasajero);
                    System.out.println("Pasajero agregado con éxito al vuelo.");
                }
                case 5 -> {
                    System.out.print("Ingrese el nombre del aeropuerto: ");
                    String nombreAeropuerto = scanner.nextLine();
                    List<Vuelo> vuelos = sistema.cargarVuelos(nombreAeropuerto);
                    if (!vuelos.isEmpty()) {
                        System.out.println("\nVuelos del aeropuerto:");
                        for (Vuelo v : vuelos) {
                            System.out.printf("[%s] De %s a %s | Salida: %s | Llegada: %s\n",
                                    v.getNumeroVuelo(), v.getOrigen(), v.getDestino(), v.getHoraSalida(), v.getHoraLlegada());
                        }
                    } else {
                        System.out.println("No se encontraron vuelos para el aeropuerto especificado.");
                    }
                }
                case 6 -> {
                    System.out.print("Ingrese el número del vuelo: ");
                    String numeroVuelo = scanner.nextLine();

                    // Obtener detalles del vuelo
                    Vuelo vuelo = null;
                    String aeropuertoOrigen = null;
                    for (Aeropuerto aeropuerto : sistema.getListaAeropuertos()) {
                        List<Vuelo> vuelos = sistema.cargarVuelos(aeropuerto.getNombre());
                        for (Vuelo v : vuelos) {
                            if (v.getNumeroVuelo().equalsIgnoreCase(numeroVuelo)) {
                                vuelo = v;
                                aeropuertoOrigen = aeropuerto.getNombre();
                                break;
                            }
                        }
                        if (vuelo != null) break;
                    }

                    if (vuelo == null) {
                        System.out.println("Vuelo no encontrado.");
                        break;
                    }

                    System.out.printf("\nDetalles del Vuelo [%s]:\n", numeroVuelo);
                    System.out.printf("Origen: %s (Aeropuerto: %s)\nDestino: %s\nSalida: %s\nLlegada: %s\nCapacidad Máxima: %d\n",
                            vuelo.getOrigen(), aeropuertoOrigen, vuelo.getDestino(), vuelo.getHoraSalida(), vuelo.getHoraLlegada(), vuelo.getCapacidadMaxima());

                    // Obtener pilotos
                    List<Piloto> pilotos = sistema.obtenerPilotosDeVuelo(numeroVuelo);
                    System.out.println("\nPilotos asignados:");
                    if (!pilotos.isEmpty()) {
                        for (Piloto piloto : pilotos) {
                            System.out.printf("Nombre: %s | Licencia: %s\n", piloto.getNombre(), piloto.getLicencia());
                        }
                    } else {
                        System.out.println("No hay pilotos asignados.");
                    }

                    // Obtener pasajeros y su equipaje
                    List<Pasajero> pasajeros = sistema.cargarPasajeros(numeroVuelo);
                    System.out.println("\nPasajeros:");
                    if (!pasajeros.isEmpty()) {
                        for (Pasajero pasajero : pasajeros) {
                            System.out.printf("Nombre: %s | Documento: %s\n", pasajero.getNombre(), pasajero.getDocumento());
                            List<Equipaje> equipajes = sistema.obtenerEquipajeDePasajero(pasajero.getDocumento());
                            System.out.println("  Equipaje:");
                            if (!equipajes.isEmpty()) {
                                for (Equipaje equipaje : equipajes) {
                                    System.out.printf("    Peso: %.2f | Frágil: %b\n", equipaje.getPeso(), equipaje.isEsFragil());
                                }
                            } else {
                                System.out.println("    Sin equipaje.");
                            }
                        }
                    } else {
                        System.out.println("No hay pasajeros en este vuelo.");
                    }
                }
                case 7 -> {
                    System.out.print("Ingrese el documento del pasajero a buscar: ");
                    String documento = scanner.nextLine();
                    boolean encontrado = false;
                    for (Aeropuerto aeropuerto : sistema.getListaAeropuertos()) {
                        List<Vuelo> vuelos = sistema.cargarVuelos(aeropuerto.getNombre());
                        for (Vuelo vuelo : vuelos) {
                            List<Pasajero> pasajeros = sistema.cargarPasajeros(vuelo.getNumeroVuelo());
                            for (Pasajero pasajero : pasajeros) {
                                if (pasajero.getDocumento().equals(documento)) {
                                    System.out.println("\nPasajero encontrado:");
                                    System.out.printf("Nombre: %s | Documento: %s | Vuelo: %s (De %s a %s)\n",
                                            pasajero.getNombre(), pasajero.getDocumento(), vuelo.getNumeroVuelo(), vuelo.getOrigen(), vuelo.getDestino());
                                    encontrado = true;
                                }
                            }
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Pasajero no encontrado.");
                    }
                }
                case 8 -> {
                    System.out.print("Ingrese el número del vuelo: ");
                    String numeroVuelo = scanner.nextLine();
                    List<Piloto> pilotos = sistema.obtenerPilotosDeVuelo(numeroVuelo);
                    if (pilotos.size() >= 2) {
                        System.out.println("El vuelo ya tiene asignados dos pilotos.");
                    } else {
                        System.out.print("Ingrese el nombre del piloto: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese la licencia del piloto: ");
                        String licencia = scanner.nextLine();

                        Piloto piloto = new Piloto(nombre, licencia, numeroVuelo);
                        sistema.agregarPiloto(piloto);
                        System.out.println("Piloto asignado con éxito.");
                    }
                }
                case 9 -> {
                   

                    System.out.print("Ingrese el documento del pasajero: ");
                    String documento = scanner.nextLine();
                    List<Equipaje> equipajes = sistema.obtenerEquipajeDePasajero(documento);
                    if (equipajes.size() >= 3) {
                        System.out.println("El pasajero ya tiene asignados tres equipajes.");
                    } else {
                        System.out.print("Ingrese el peso del equipaje: ");
                        double peso = scanner.nextDouble();
                        System.out.print("¿El equipaje es frágil? (true/false): ");
                        boolean esFragil = scanner.nextBoolean();
                        scanner.nextLine(); // Limpiar buffer

                        Equipaje equipaje = new Equipaje(documento, peso, esFragil);
                        sistema.agregarEquipaje(equipaje);
                        System.out.println("Equipaje agregado con éxito.");
                    }
                }
                case 10 -> {
                    System.out.println("Saliendo del sistema. Hasta luego.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}
