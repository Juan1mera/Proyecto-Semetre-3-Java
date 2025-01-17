package modelo;

import java.io.Serializable;

public class Pasajero implements Serializable {
    private String nombre;
    private String documento;
    private String pasaporte;
    private String edad;
    private String clase;
    private String vueloAsignado;

    public Pasajero(String nombre, String documento, String pasaporte, String edad, String clase, String vueloAsignado) {
        this.nombre = nombre;
        this.documento = documento;
        this.pasaporte = pasaporte;
        this.edad = edad;
        this.clase = clase;
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

    public String getPasaporte() {
        return pasaporte;
    }
    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getClase() {
        return clase;
    }
    public void setClase(String clase) {
        this.clase = clase;
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
                ", pasaporte='" + pasaporte + '\'' +
                ", edad='" + edad + '\'' +
                ", clase='" + clase + '\'' +
                ", vueloAsignado='" + vueloAsignado + '\'' +
                '}';
    }
}
