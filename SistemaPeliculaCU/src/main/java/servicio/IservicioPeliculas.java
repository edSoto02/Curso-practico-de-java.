package servicio;

import domino.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class IservicioPeliculas implements ServicioPeliculas{

    private final List<Pelicula> peliculas;

    public IservicioPeliculas() {
        this.peliculas = new ArrayList<>();
    }

    @Override
    public void listarPeliculas() {
        System.out.println("Listado de Peliculas...");
        peliculas.forEach(System.out::println);
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
        System.out.println("Se agreggo la pelicula: " + pelicula );

    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var indice = peliculas.indexOf(pelicula);
        System.out.println("Pelicula con el indice: " + indice);

    }
}
