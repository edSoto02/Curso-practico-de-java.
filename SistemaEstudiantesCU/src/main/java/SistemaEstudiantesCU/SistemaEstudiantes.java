package SistemaEstudiantesCU;

import datos.EstudianteDAO;
import dominio.Estudiante;


import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class SistemaEstudiantes {

    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        //Se crea una instancia de la clase EstudianteDAO
        var estudianteDAO = new EstudianteDAO();

        while (!salir){
            try {
                mostrarMenu();
                salir = ejecutarOpciones(consola, estudianteDAO);
            }catch (Exception e){
                System.out.println("Error al ejecutar la operacion "+ e.getMessage());

            }
        }//fin while
        System.out.println();
    }

    private static void mostrarMenu(){
        System.out.print("""
                *** Sistema de Estudiantes ***
                1. Listar Estudiantes
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elige una opcion:\s
                """);
    }

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;

        switch (opcion){
            case 1 ->{
                System.out.println("Listado de Estudinates: ");
                var estudiantes =  estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }// fin case 1
            case 2 ->{
                System.out.println("Introduce el ID a buscar: ");
                var idBuscar = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idBuscar);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if (encontrado){
                    System.out.println("Estudiante Encontrado: " + estudiante);
                }else{
                    System.out.println("Estudiante No Encontrado: ");
                }
            }//fin case 2
            case 3 ->{
                System.out.println("Agregando Estudiante: ");
                System.out.print("Ingresa el Nombre");
                var nombre = consola.nextLine();
                System.out.print("Ingresa el Apellido");
                var apellido = consola.nextLine();
                System.out.print("Ingresa el Telefono");
                var telefono = consola.nextLine();
                System.out.print("Ingresa el Email");
                var email = consola.nextLine();
                //creamos el objeto estudiante si id
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if (agregado){
                    System.out.println("Estudiante Agregado: " + estudiante);
                }else{
                    System.out.println("Estudiante NO Agregado: " + estudiante);
                }
            }//fin case 3
            case 4 ->{
                System.out.println("Modificando Estudiante");
                System.out.print("Ingresa el ID");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.print("Modifica el Nombre");
                var nombre = consola.nextLine();
                System.out.print("Modifica el Apellido");
                var apellido = consola.nextLine();
                System.out.print("Modifica el Telefono");
                var telefono = consola.nextLine();
                System.out.print("Modifica el Email");
                var email = consola.nextLine();
                //creamo el objeto para modificar el estudiante
                var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                if (modificado){
                    System.out.println("Estudiante Actualizado: " + estudiante);
                }else{
                    System.out.println("Estudiante NO agregado: " + estudiante);
                }
            }//fin case 4
            case 5 ->{
                System.out.println("Eliminando Estudiante: ");
                System.out.println("Ingresa el id ");
                var eliminar = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(eliminar);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);

                if (eliminado){
                    System.out.println("Estudiante Eliminado: " + estudiante);
                }else{
                    System.out.println("Estudiante NO Eliminaado: " + estudiante);
                }
            }//fin case 5
            case 6 ->{
                System.out.print("Hasta Pronto: ");
                salir = true;
            }
            default -> System.out.println("Opcion Incorrecta: ");

        }//fin switch
        return salir;
    }//fin ejecutar opciones
}