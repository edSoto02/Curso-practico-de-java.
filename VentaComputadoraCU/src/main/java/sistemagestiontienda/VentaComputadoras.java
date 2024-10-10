package sistemagestiontienda;

import modelo.Computadora;
import modelo.Monitor;
import modelo.Raton;
import modelo.Teclado;
import servicio.Orden;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class VentaComputadoras {
    public static void main(String[] args) {

        Raton raton1 = new Raton("bluethoo", "sony");
        Raton raton2 = new Raton("usb", "dell");
        Raton raton3 = new Raton("usb", "HP");

        Teclado teclado1 = new Teclado("alambrico", "sony");
        Teclado teclado2 = new Teclado("usb", "dell");
        Teclado teclado3 = new Teclado("usb", "HP");

        Monitor monitor1 = new Monitor("Sony", 22);
        Monitor monitor2 = new Monitor("dell", 18);
        Monitor monitor3 = new Monitor("HP", 22);

        Computadora computadora1 =
                new Computadora("Sony", monitor1, teclado1, raton1);
        Computadora computadora2 =
                new Computadora("Dell", monitor2, teclado2, raton2);
        Computadora computadora3 =
                new Computadora("HP", monitor3, teclado3, raton3);

        Orden orden1 = new Orden();
        orden1.agregarComputadora(computadora1);
        orden1.mostrarOrden();
        System.out.println();

        Orden orden2 = new Orden();
        orden2.agregarComputadora(computadora1);
        orden2.agregarComputadora(computadora2);
        orden2.agregarComputadora(computadora3);
        orden2.mostrarOrden();


    }
}