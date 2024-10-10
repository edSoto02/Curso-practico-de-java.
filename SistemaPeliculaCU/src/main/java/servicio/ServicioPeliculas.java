package servicio;

import domino.Pelicula;

public interface ServicioPeliculas {

    public void listarPeliculas();

    public void agregarPelicula(Pelicula pelicula);

    public void buscarPelicula(Pelicula pelicula);


}
