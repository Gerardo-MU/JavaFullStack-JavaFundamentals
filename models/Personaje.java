package models;

public abstract class Personaje {

    // ------------------------------------
    //       PROPIEDADES
    // ------------------------------------
    private String nombre;
    private int nivel;
    protected int puntosDeVida;
    private static int totalPersonajesCreados = 0;

    // ------------------------------------
    //       CONSTRUCTOR
    // ------------------------------------
    public Personaje(String nombre, int nivel, int puntosDeVida) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntosDeVida = puntosDeVida;
        totalPersonajesCreados++;
    }

    // ------------------------------------
    //       GETTERS/SETTERS
    // ------------------------------------
    public String getNombre() {
        return nombre;
    }
    public int getNivel() {
        return nivel;
    }
    public int getPuntosDeVida() {
        return puntosDeVida;
    }

    // ------------------------------------
    //       MÉTODOS ABSTRACTOS
    // ------------------------------------
    public abstract void atacar(Personaje objetivo);

    // ------------------------------------
    //       MÉTODOS
    // ------------------------------------
    // Método de recibir daño
    public void recibirDanio(int danio) {
        this.puntosDeVida -= danio;
        System.out.println(getNombre() + " recibe " + danio + " de daño. Vida restante: " + puntosDeVida);
    }
    //Método para establecer puntos de vida
    public void setPuntosDeVida(int puntosDeVida) {
        this.puntosDeVida = puntosDeVida;
    }
    //Metodo defender
    public void defender() {
        System.out.println(getNombre() + " se pone en posición defensiva.");
    }

    // ------------------------------------
    //       MÉTODOS ESTATICOS
    // ------------------------------------
    //Método estatico para obtener personajes creados
    public static int getTotalPersonajesCreados() {
        return totalPersonajesCreados;
    }

    // ------------------------------------
    //       SOBRECARGA
    // ------------------------------------
    // Sobrecarga de metodo atacar
    public void atacar(Personaje objetivo, int potenciaExtra) {
        System.out.println(nombre + " ataca con potencia extra: " + potenciaExtra);
        atacar(objetivo); // llama al método abstracto
        objetivo.recibirDanio(potenciaExtra);
    }
    //Sobrecarga de toString()
    @Override
    public String toString() {
        return "Nombre: " + nombre + 
               ", Nivel: " + nivel + 
               ", Puntos de Vida: " + puntosDeVida;
    }
}
