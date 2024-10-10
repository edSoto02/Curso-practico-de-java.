package sistemagestiontienda;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class CalcualdroaApp {
    public static void main(String[] args) {

        Scanner consola = new Scanner(System.in);

        while (true){
            System.out.println("CALCULADORA");
            MostrarMenu();
            try {
                var operacion = consola.nextInt();
                if (operacion >= 1 && operacion <=4){
                    ejecutarOperacion(operacion,consola);
                }else if (operacion == 5){
                    System.out.println("Saliendo....");
                    break;
                }else{
                    System.out.println("Opcion Incorrecta:");
                }
                System.out.println();
            }catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void MostrarMenu(){
        System.out.println("""
                1.SUMA
                2.RESTA
                3.MULTIPLICACION
                4.DIVISION
                5.SALIR
                
                """);
        System.out.print("SELECCIONA UNA OPCION: ");
    }

    private static  void ejecutarOperacion(int operacion ,Scanner consola){
        System.out.println("Ingresa el primer valor:");
        var operador1 = Double.parseDouble(consola.nextLine());
        System.out.println("Ingresa el segundo valor:");
        var operador2 = Double.parseDouble(consola.nextLine());
        double resultado ;

        switch (operacion){
            case 1 -> {
                resultado = operador1 + operador2;
                System.out.println("El Resultado es: " + resultado);
            }
            case 2 -> {
                resultado = operador1 - operador2;
                System.out.println("El Resultado es: " + resultado);
            }
            case 3 -> {
                resultado = operador1 * operador2;
                System.out.println("El Resultado es: " + resultado);
            }
            case 4 -> {
                resultado = operador1 / operador2;
                System.out.println("El Resultado es: " + resultado);
            }
            default ->
                    System.out.println("Opcion Incorrecta: ");

        }
    }
}