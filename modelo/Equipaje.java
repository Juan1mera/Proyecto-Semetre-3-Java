package modelo;

import java.io.Serializable;

public class Equipaje implements Serializable {
    private String propietario; // Nombre del propietario del equipaje
    private double peso;        // Peso del equipaje
    private boolean esFragil;   // Si el equipaje es frágil

    public Equipaje(String propietario, double peso, boolean esFragil) {
        this.propietario = propietario;
        this.peso = peso;
        this.esFragil = esFragil;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isEsFragil() {
        return esFragil;
    }

    public void setEsFragil(boolean esFragil) {
        this.esFragil = esFragil;
    }

    @Override
    public String toString() {
        return "Equipaje{" +
                "propietario='" + propietario + '\'' +
                ", peso=" + peso +
                ", esFragil=" + esFragil +
                '}';
    }
}
