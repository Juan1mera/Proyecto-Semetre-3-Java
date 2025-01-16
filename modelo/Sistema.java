package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private final List<Aeropuerto> listaAeropuertos;

    // Archivos de almacenamiento
    private static final String AEROPUERTOS_FILE = "aeropuertos.txt";
    private static final String VUELOS_FILE = "vuelos.txt";
    private static final String PASAJEROS_FILE = "pasajeros.txt";
    private static final String EQUIPAJE_FILE = "equipaje.txt";
    private static final String PILOTOS_FILE = "pilotos.txt";

    public Sistema() {
        // Verificar y crear archivos si no existen
        inicializarArchivos();
        // Cargar los aeropuertos al iniciar el sistema
        this.listaAeropuertos = cargarAeropuertos();
    }

    private void inicializarArchivos() {
        try {
            new File(AEROPUERTOS_FILE).createNewFile();
            new File(VUELOS_FILE).createNewFile();
            new File(PASAJEROS_FILE).createNewFile();
            new File(EQUIPAJE_FILE).createNewFile();
            new File(PILOTOS_FILE).createNewFile();
        } catch (IOException e) {
            System.out.println("Error al inicializar los archivos: " + e.getMessage());
        }
    }

    // ----------------------- Aeropuertos -----------------------
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

    // ----------------------- Vuelos -----------------------
    public void agregarVuelo(String nombreAeropuerto, Vuelo vuelo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VUELOS_FILE, true))) {
            writer.write(nombreAeropuerto + "|" + vuelo.getNumeroVuelo() + "|" + vuelo.getOrigen() + "|" + vuelo.getDestino() +
                    "|" + vuelo.getHoraSalida() + "|" + vuelo.getHoraLlegada() + "|" + vuelo.getCapacidadMaxima());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el vuelo: " + e.getMessage());
        }
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

    // ----------------------- Pasajeros -----------------------
    public void agregarPasajero(String numeroVuelo, Pasajero pasajero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASAJEROS_FILE, true))) {
            writer.write(numeroVuelo + "|" + pasajero.getNombre() + "|" + pasajero.getDocumento() + "|" + pasajero.getVueloAsignado());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el pasajero: " + e.getMessage());
        }
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

    // ----------------------- Equipaje -----------------------
    public void agregarEquipaje(Equipaje equipaje) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EQUIPAJE_FILE, true))) {
            writer.write(equipaje.getPropietario() + "|" + equipaje.getPeso() + "|" + equipaje.isEsFragil());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el equipaje: " + e.getMessage());
        }
    }

    public List<Equipaje> obtenerEquipajeDePasajero(String propietario) {
        List<Equipaje> listaEquipaje = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(EQUIPAJE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equalsIgnoreCase(propietario)) {
                    listaEquipaje.add(new Equipaje(parts[0], Double.parseDouble(parts[1]), Boolean.parseBoolean(parts[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el equipaje: " + e.getMessage());
        }
        return listaEquipaje;
    }

    // ----------------------- Pilotos -----------------------
    public void agregarPiloto(Piloto piloto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PILOTOS_FILE, true))) {
            writer.write(piloto.getNombre() + "|" + piloto.getLicencia() + "|" + piloto.getVueloAsignado());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el piloto: " + e.getMessage());
        }
    }

    public List<Piloto> obtenerPilotosDeVuelo(String numeroVuelo) {
        List<Piloto> listaPilotos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PILOTOS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[2].equalsIgnoreCase(numeroVuelo)) {
                    listaPilotos.add(new Piloto(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los pilotos: " + e.getMessage());
        }
        return listaPilotos;
    }
}
