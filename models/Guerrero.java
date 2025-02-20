package models;

public class Guerrero extends Personaje {

    public Guerrero(String nombre, int nivel, int puntosDeVida) {
        super(nombre, nivel, puntosDeVida);
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println(getNombre() + " (Guerrero) ataca con su espada a " + objetivo.getNombre());
        objetivo.recibirDanio(12); 
    }
}
