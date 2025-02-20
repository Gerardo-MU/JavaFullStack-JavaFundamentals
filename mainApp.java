import models.*;
import UI.ArchivoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class mainApp {

    //Lista de personajes creados por el usuario
    private static List<Personaje> personajes = new ArrayList<>();
    // Lista de enemigos leídos desde el archivo
    private static List<Enemigo> enemigos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        enemigos = ArchivoUtil.leerEnemigosDesdeArchivo("enemigos.txt");
        System.out.println("Se han cargado " + enemigos.size() + " enemigos desde el archivo.");

        //Bucle principal de la aplicación
        while (!salir) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Crear un nuevo personaje");
            System.out.println("2. Ver todos los personajes");
            System.out.println("3. Pelear contra un enemigo aleatorio");
            System.out.println("4. Salir");
            System.out.print("\nElige una opción: ");

            String opcion = sc.nextLine();

            //Evalua la desición del usuario del menú principal
            switch (opcion) {
                case "1":
                    crearPersonaje(sc);
                    break;
                case "2":
                    mostrarPersonajes();
                    break;
                case "3":
                    if (personajes.isEmpty()) {
                        System.out.println("No hay personajes creados. Crea uno primero.");
                    } else if (enemigos.isEmpty()) {
                        System.out.println("No hay enemigos cargados. Verifica el archivo.");
                    } else {
                        pelearContraEnemigoRandom(sc);
                    }
                    break;
                case "4":
                    salir = true;
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        sc.close();
    }

    // ------------------------------------
    //       MÉTODOS DE CREACIÓN
    // ------------------------------------
    private static void crearPersonaje(Scanner sc) {
        System.out.println("\n=== CREAR PERSONAJE ===");
        System.out.println("Elige la clase de tu personaje: ");
        System.out.println("1. Guerrero (HP base: 100)");
        System.out.println("2. Mago (HP base: 80, Maná: 50)");
        System.out.println("3. Arquero (HP base: 90, Flechas: 20)");
        System.out.print("\nElige una opción: ");

        String opcionClase = sc.nextLine();

        //Obtiene desición del usuario
        System.out.print("\nIntroduce el nombre de tu personaje: ");
        String nombre = sc.nextLine();

        Personaje nuevoPersonaje = null;

        //Se evalua el tipo de personaje seleccionado
        switch (opcionClase) {
            case "1":
                nuevoPersonaje = new Guerrero(nombre, 1, 100);
                break;
            case "2":
                nuevoPersonaje = new Mago(nombre, 1, 80, 50);
                break;
            case "3":
                nuevoPersonaje = new Arquero(nombre, 1, 90, 20);
                break;
            default:
                System.out.println("Opción de clase no válida. Regresando al menú.");
                return;
        }

        //Añade un nuevo personaje
        personajes.add(nuevoPersonaje);
        System.out.println("\n¡Personaje creado exitosamente!");
        System.out.println('\n' + nuevoPersonaje.toString());
        System.out.println("Total de personajes creados: " + Personaje.getTotalPersonajesCreados());
    }

    private static void mostrarPersonajes() {
        System.out.println("\n=== LISTA DE PERSONAJES ===");
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes creados.");
        } else {
            for (int i = 0; i < personajes.size(); i++) {
                Personaje p = personajes.get(i);
                System.out.println((i + 1) + ". " + p.toString());
            }
        }
    }

    // ------------------------------------
    //      MÉTODO DE BATALLA
    // ------------------------------------
    private static void pelearContraEnemigoRandom(Scanner sc) {
        System.out.println("\n=== PELEA CONTRA ENEMIGO ALEATORIO ===");
        
        // 1. Elige el personaje con el que se luchará
        mostrarPersonajes();
        System.out.print("\nElige el número de tu personaje para combatir: ");
        int indicePersonaje = Integer.parseInt(sc.nextLine()) - 1;

        if (indicePersonaje < 0 || indicePersonaje >= personajes.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Personaje personaje = personajes.get(indicePersonaje);

        // 2. Selecciona un enemigo al azar
        Random random = new Random();
        int indiceEnemigo = random.nextInt(enemigos.size());
        Enemigo enemigo = enemigos.get(indiceEnemigo);// Crea enemigo

        System.out.println("¡Te enfrentas a " + enemigo.getNombre() + " (Tipo: " + enemigo.toString() + ")!");

        // 3. Iniciar el bucle de batalla
        //    Se alternan turnos mientras aun tengan puntos de vida.
        while (personaje.getPuntosDeVida() > 0 && enemigo.getPuntosDeVida() > 0) {
            // Turno del personaje
            System.out.println("\nTu vida: " + personaje.getPuntosDeVida() + " | Vida enemigo: " + enemigo.getPuntosDeVida());
            System.out.print("\n¿Qué deseas hacer? (A) Atacar / (D) Defender: ");
            String accion = sc.nextLine().toUpperCase();

            if (accion.equals("A")) {
                // Atacar
                personaje.atacar(enemigo);
            } else if (accion.equals("D")) {
                // Defender
                personaje.defender();
            } else {
                System.out.println("Opción no válida, se pasa turno sin atacar ni defender...");
            }

            // Verificar si el enemigo ha muerto
            if (enemigo.getPuntosDeVida() <= 0) {
                System.out.println("\n¡Has derrotado al enemigo " + enemigo.getNombre() + "!");
                break;
            }

            // Turno del enemigo
            System.out.println("\n" + enemigo.getNombre() + " se prepara para atacar...");

            int dañoBase = 8;
            if (accion.equals("D")) {
                System.out.println(personaje.getNombre() + " se defendió y reduce el daño a la mitad.");
                dañoBase /= 2;
            }
            // Realiza el ataque
            enemigo.atacar(personaje);

            // Verificar si el personaje ha muerto
            if (personaje.getPuntosDeVida() <= 0) {
                System.out.println("\n" + personaje.getNombre() + " ha sido derrotado por " + enemigo.getNombre() + "...");
                break;
            }
        }

        // Fin de la batalla
        System.out.println("\n=== FIN DE LA BATALLA ===");
        System.out.println("Estado final de " + personaje.getNombre() + ": " + personaje.getPuntosDeVida() + " HP");
        System.out.println("Estado final de " + enemigo.getNombre() + ": " + enemigo.getPuntosDeVida() + " HP");
    }
}
