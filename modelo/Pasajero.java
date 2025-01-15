package modelo;

import java.io.Serializable;

public class Pasajero implements Serializable {
    private String nombre;
    private String documento;
    private String vueloAsignado;

    public Pasajero(String nombre, String documento, String vueloAsignado) {
        this.nombre = nombre;
        this.documento = documento;
        this.vueloAsignado = vueloAsignado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getVueloAsignado() {
        return vueloAsignado;
    }

    public void setVueloAsignado(String vueloAsignado) {
        this.vueloAsignado = vueloAsignado;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", vueloAsignado='" + vueloAsignado + '\'' +
                '}';
    }
}
