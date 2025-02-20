package UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Enemigo;

//Clase leer archivo de los enemigos
public class ArchivoUtil {

    //Método estático de la clase
    public static List<Enemigo> leerEnemigosDesdeArchivo(String ruta) {

        //Lista que ontiene el objeto Enemigo
        List<Enemigo> listaEnemigos = new ArrayList<>();

        //Manejo de excepciones
        try {

            File archivo = new File(ruta);
            FileReader fileReader= new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;

            //Bucle de lectura
            while ((linea = bufferedReader.readLine()) != null) {

                String[] partes = linea.split(";");
                String nombre = partes[0];
                int nivel = Integer.parseInt(partes[1]);
                int vida = Integer.parseInt(partes[2]);
                String tipo = partes[3];

                //Alamacenamiento en la lista
                Enemigo e = new Enemigo(nombre, nivel, vida, tipo);
                listaEnemigos.add(e);
            }
            bufferedReader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("\n El archivo no se encuentra");
        } catch (IOException e) {
            System.out.println("\n Ocurrio un error");
        }
        return listaEnemigos;
    }
}
