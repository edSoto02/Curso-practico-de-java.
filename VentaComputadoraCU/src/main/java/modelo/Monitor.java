package modelo;

public class Monitor {

    private final int idMonitor;
    private String marca;
    private double tamanio;

    private static int contadorMoitores;

    private Monitor(){
        idMonitor = ++ contadorMoitores;
    }

    public Monitor(String marca, double tamanio) {
        this();
        this.marca = marca;
        this.tamanio = tamanio;
    }

    public int getIdMonitor() {
        return idMonitor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getTamanio() {
        return tamanio;
    }

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

    public static int getContadorMoitores() {
        return contadorMoitores;
    }

    public static void setContadorMoitores(int contadorMoitores) {
        Monitor.contadorMoitores = contadorMoitores;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "idMonitor=" + idMonitor +
                ", marca='" + marca + '\'' +
                ", tamanio=" + tamanio +
                '}';
    }
}
