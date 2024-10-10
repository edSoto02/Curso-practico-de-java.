package servicio;

import domino.Pelicula;

import java.io.*;

public class ServicioDeArchivoPeliculas implements ServicioPeliculas {

    private final String nombreArchivo = "peliculas.txt";

    public ServicioDeArchivoPeliculas() {
        var archivo = new File(nombreArchivo);
        try {
            if (archivo.exists()){
                System.out.println("Ya existe: ");
            }else{
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado");
            }
        }catch (Exception e){
            System.out.println("Eror" + e);

        }
    }



    @Override
    public void listarPeliculas() {
        var archivo = new File(nombreArchivo);
        try {
            System.out.println("Listado de Peliculas");
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea;
            linea = entrada.readLine();
            while (linea != null){
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                linea = entrada.readLine();
            }
            entrada.close();
        }catch (Exception e ){
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        boolean anexar = false;
        var archivo = new File(nombreArchivo);

        try {
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo), anexar);
            salida.println(pelicula);
            salida.close();
            System.out.println("Se agrego al archivo ");
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var archivo = new File(nombreArchivo);

        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            var indice = 1;
            var encontrada = false;
            var buscar = pelicula.getNombre();

            while (lineaTexto != null){
                if (pelicula != null && pelicula.equals(lineaTexto)){
                    encontrada = true;
                    break;
                }
                lineaTexto = entrada.readLine();
                indice++;

            }//fin while
            if (encontrada){
                System.out.println("pelicula " + lineaTexto + "encontrada en  "+ indice);
            }else{
                System.out.println("Error de pelicula." + pelicula.getNombre());
            }
            entrada.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
