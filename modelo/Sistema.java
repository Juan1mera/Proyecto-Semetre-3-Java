package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private final List<Aeropuerto> listaAeropuertos;
    private static final String AEROPUERTOS_FILE = "aeropuertos.txt";
    private static final String VUELOS_FILE = "vuelos.txt";
    private static final String PASAJEROS_FILE = "pasajeros.txt";

    public Sistema() {
        this.listaAeropuertos = cargarAeropuertos();
    }

    public void agregarAeropuerto(Aeropuerto aeropuerto) {
        listaAeropuertos.add(aeropuerto);
        guardarAeropuertos();
    }

    public List<Aeropuerto> getListaAeropuertos() {
        return listaAeropuertos;
    }

    public Aeropuerto buscarAeropuerto(String nombre) {
        for (Aeropuerto aeropuerto : listaAeropuertos) {
            if (aeropuerto.getNombre().equalsIgnoreCase(nombre)) {
                return aeropuerto;
            }
        }
        return null;
    }

    public void agregarVuelo(String nombreAeropuerto, Vuelo vuelo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VUELOS_FILE, true))) {
            writer.write(nombreAeropuerto + "|" + vuelo.getNumeroVuelo() + "|" + vuelo.getOrigen() + "|" + vuelo.getDestino() +
                    "|" + vuelo.getHoraSalida() + "|" + vuelo.getHoraLlegada() + "|" + vuelo.getCapacidadMaxima());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el vuelo: " + e.getMessage());
        }
    }

    public void agregarPasajero(String numeroVuelo, Pasajero pasajero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASAJEROS_FILE, true))) {
            writer.write(numeroVuelo + "|" + pasajero.getNombre() + "|" + pasajero.getDocumento() + "|" + pasajero.getVueloAsignado());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el pasajero: " + e.getMessage());
        }
    }

    private void guardarAeropuertos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AEROPUERTOS_FILE))) {
            for (Aeropuerto aeropuerto : listaAeropuertos) {
                writer.write("Aeropuerto|" + aeropuerto.getNombre() + "|" + aeropuerto.getUbicacion() + "|" + aeropuerto.getCapacidad());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los aeropuertos: " + e.getMessage());
        }
    }

    private List<Aeropuerto> cargarAeropuertos() {
        List<Aeropuerto> aeropuertos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(AEROPUERTOS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if ("Aeropuerto".equals(parts[0])) {
                    aeropuertos.add(new Aeropuerto(parts[1], parts[2], Integer.parseInt(parts[3])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los aeropuertos: " + e.getMessage());
        }
        return aeropuertos;
    }

    public List<Vuelo> cargarVuelos(String nombreAeropuerto) {
        List<Vuelo> vuelos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(VUELOS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equalsIgnoreCase(nombreAeropuerto)) {
                    vuelos.add(new Vuelo(parts[1], parts[2], parts[3], parts[4], parts[5], Integer.parseInt(parts[6])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los vuelos: " + e.getMessage());
        }
        return vuelos;
    }

    public List<Pasajero> cargarPasajeros(String numeroVuelo) {
        List<Pasajero> pasajeros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PASAJEROS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equalsIgnoreCase(numeroVuelo)) {
                    pasajeros.add(new Pasajero(parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los pasajeros: " + e.getMessage());
        }
        return pasajeros;
    }
}
