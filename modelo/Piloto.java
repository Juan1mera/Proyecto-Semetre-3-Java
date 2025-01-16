package modelo;

import java.io.Serializable;

public class Piloto implements Serializable {
    private String nombre;         // Nombre del piloto
    private String licencia;       // Número de licencia del piloto
    private String vueloAsignado;  // Código del vuelo asignado al piloto

    public Piloto(String nombre, String licencia, String vueloAsignado) {
        this.nombre = nombre;
        this.licencia = licencia;
        this.vueloAsignado = vueloAsignado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getVueloAsignado() {
        return vueloAsignado;
    }

    public void setVueloAsignado(String vueloAsignado) {
        this.vueloAsignado = vueloAsignado;
    }

    @Override
    public String toString() {
        return "Piloto{" +
                "nombre='" + nombre + '\'' +
                ", licencia='" + licencia + '\'' +
                ", vueloAsignado='" + vueloAsignado + '\'' +
                '}';
    }
}
