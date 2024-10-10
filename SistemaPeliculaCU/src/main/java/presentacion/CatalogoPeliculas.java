package presentacion;

import domino.Pelicula;
import servicio.IservicioPeliculas;
import servicio.ServicioDeArchivoPeliculas;
import servicio.ServicioPeliculas;


import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class CatalogoPeliculas {
    public static void main(String[] args) {

        var salir = false;
        var consola = new Scanner(System.in);
        //IservicioPeliculas iservicioPeliculas = new IservicioPeliculas();
        ServicioPeliculas servicioPeliculas = new ServicioDeArchivoPeliculas();


        while (!salir){

            try {
                mostrarMenu();
                salir = ejecutarOpciones(consola, servicioPeliculas);
            }catch (Exception e){
                System.out.println("Error " + e);
            }
            System.out.println();
        }//fin while
    }

    private static void mostrarMenu(){
        System.out.print("""
                CATALOGO DE PELICULAS
                1 Agregar
                2 Listar
                3 Buscar
                4 Salir
                **SELECCIONA UNA OPCION**
                """);
    }

    private static boolean ejecutarOpciones(Scanner consola, ServicioPeliculas peliculas){

        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;

        switch (opcion){
            case 1 ->{
                System.out.println("Introduce el nombre de la Pelicula: ");
                var nombrePelicula = consola.nextLine();
                peliculas.agregarPelicula(new Pelicula(nombrePelicula));
            }
            case 2 -> peliculas.listarPeliculas();

            case 3 ->{
                System.out.println("Introduce la Pelicula a buscar: ");
                var buscar = consola.nextLine();
                peliculas.buscarPelicula(new Pelicula(buscar));

            }
            case  4 ->{
                System.out.println("Saliendo");
                salir = true;
            }
            default -> System.out.println("Valor incorrecto " + opcion);
        }

        return salir;
    }

}