package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {

    public static <T> void guardarEnArchivo(String fileName, List<T> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (T item : lista) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los datos en el archivo: " + e.getMessage());
        }
    }

    public static List<String> cargarDesdeArchivo(String fileName) {
        List<String> lista = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return lista; // Devuelve una lista vacía si no existe el archivo
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lista.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos desde el archivo: " + e.getMessage());
        }
        return lista;
    }
}
