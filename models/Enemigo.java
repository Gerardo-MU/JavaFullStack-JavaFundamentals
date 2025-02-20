package models;

public class Enemigo extends Personaje {

    private String tipo; // Ejemplo: "Goblin", "Troll", "Dragón", etc.

    //Constructor sin tipo
    public Enemigo(String nombre, int nivel, int puntosDeVida) {
        super(nombre, nivel, puntosDeVida);
    }

    // Constructor con tipo de enemigo
    public Enemigo(String nombre, int nivel, int puntosDeVida, String tipo) {
        super(nombre, nivel, puntosDeVida);
        this.tipo = tipo;
    }

    // Getter/Setter
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Método para recibir daño (si quieres seguir el mismo patrón que Guerrero/Mago)
    public void recibirDanio(int danio) {
        this.puntosDeVida -= danio;
        System.out.println(getNombre() + " recibe " + danio + " de daño. Vida restante: " + puntosDeVida);
    }

    // Implementación del método abstracto atacar()
    @Override
    public void atacar(Personaje objetivo) {
        System.out.println(getNombre() + " (Enemigo) ataca a " + objetivo.getNombre());
        objetivo.recibirDanio(8); 
    }
}

