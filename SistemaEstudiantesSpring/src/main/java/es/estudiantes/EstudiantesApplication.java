package es.estudiantes;

import es.estudiantes.modelo.Estudiante;
import es.estudiantes.servicio.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {//para ejecutar la aplicacion por consola
	@Autowired
	private EstudianteServicio estudianteServicio;//inyectamos las dependendiac de EstudianteServicio

	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);
    //para obetener loggger para manadar a imprimir en consola

	String nl = System.lineSeparator();
    //para imprimir los saltos del linea 

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion...");
		///Levantar la fabrica de spring
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicacion finalizada! ");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl + "Ejecutanto metodo run de Spring..." + nl);
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}//fin while

	}//fin

	private void mostrarMenu(){
		logger.info("""
			*** Sistema de Estudiantes ***
				1. Listar Estudiantes
			    2. Buscar Estudiante
				3. Agregar Estudiante
				4. Modificar Estudiante
				5. Eliminar Estudiante
				6. Salir
				Elige un opcion:""\"
				""");
	}//fin mostrar menu

	private boolean ejecutarOpciones(Scanner consola){
		var salir = false;
		var opcion = Integer.parseInt(consola.nextLine());

		switch (opcion){

			case 1 -> {
				logger.info(nl + "Listado de Estudiantes " + nl );
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + nl)));
			}//fin case 1
			case 2 -> {
				logger.info("Introduce el ID a buscars ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudiante != null){
					logger.info("Estudiante encontrado: " + estudiante + nl);
				}else{
					logger.info("Estudiante No encontrado con id: " + idEstudiante + nl);
				}
			}//fin case 2
			case 3 -> {
				logger.info("Agregar Estudiante: " + nl );
				logger.info("Id Estudinate ");

				logger.info("Nombre");
				var nomnre = consola.nextLine();
				logger.info("Apellido");
				var apellido = consola.nextLine();
				logger.info("Telefono");
				var telefono = consola.nextLine();
				logger.info("Email");
				var email = consola.nextLine();

				var estudiante = new Estudiante();
				estudiante.setNombre(nomnre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);

				estudianteServicio.guardaeEstudiante(estudiante);
				logger.info("Estudiante Agregado " + estudiante + nl);
			}//fin case 3
			case 4 -> {
				logger.info("Modificar Estudiante: " + nl );
				logger.info("Id Estudinate ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudiante != null){
					logger.info("Nombre");
					var nomnre = consola.nextLine();
					logger.info("Apellido");
					var apellido = consola.nextLine();
					logger.info("Telefono");
					var telefono = consola.nextLine();
					logger.info("Email");
					var email = consola.nextLine();

					estudiante.setNombre(nomnre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);

					estudianteServicio.guardaeEstudiante(estudiante);
					logger.info("EStudiante Modificado " + estudiante + nl);

				}else{
					logger.info("Rdtudiante No encontrado con id  " + idEstudiante);
				}
			}//fin case 4
			case 5 -> {
				logger.info("Eliminar Estudiante: " + nl );
				logger.info("Id Estudinate ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				//buscamos el id a eliminar
				var estudinate = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudinate != null){
					estudianteServicio.eliminarEstudiante(estudinate);
					logger.info("Estudiante Eliminado " + estudinate + nl);
				}else {
					logger.info("Estudiante No Encontrado " + idEstudiante);
				}
			}//fin case 5
			case 6 -> logger.info("Hasta Pronto: " + nl + nl);//fin case 6
			default -> logger.info("Opcion Incorrecta: " + opcion + nl);

		}//fin switch
		return salir;

	}//fin ejecutar opciones
}
