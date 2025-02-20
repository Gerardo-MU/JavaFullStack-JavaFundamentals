package models;

public class Arquero extends Personaje {

    private int flechas;

    public Arquero(String nombre, int nivel, int puntosDeVida, int flechas) {
        super(nombre, nivel, puntosDeVida);
        this.flechas = flechas;
    }

    // Getter/Setter
    public int getFlechas() {
        return flechas;
    }
    public void setFlechas(int flechas) {
        this.flechas = flechas;
    }

    @Override
    public void atacar(Personaje objetivo) {
        if (flechas > 0) {
            System.out.println(getNombre() + " (Arquero) dispara una flecha a " + objetivo.getNombre());
            objetivo.recibirDanio(10);
            flechas--;
        } else {
            System.out.println(getNombre() + " no tiene flechas...");
        }
    }
    @Override
    public String toString() {
        // Incluimos flechas en la descripción
        return super.toString() + ", Flechas: " + flechas;
    }
}
