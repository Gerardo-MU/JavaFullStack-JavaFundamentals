package models;

public class Mago extends Personaje {

    private int mana;

    public Mago(String nombre, int nivel, int puntosDeVida, int mana) {
        super(nombre, nivel, puntosDeVida);
        this.mana = mana;
    }

    @Override
    public void atacar(Personaje objetivo) {
        if (mana > 0) {
            System.out.println(getNombre() + " lanza un hechizo a " + objetivo.getNombre());
            objetivo.recibirDanio(15);
            mana -= 5;
        } else {
            System.out.println(getNombre() + " no tiene suficiente maná para atacar...");
        }
    }
}