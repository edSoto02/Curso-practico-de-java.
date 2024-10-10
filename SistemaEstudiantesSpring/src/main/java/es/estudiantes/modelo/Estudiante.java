package es.estudiantes.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //clase entidad que representa una base de datos.
@Data //para que se generar lo metodos get y set de manera automatica
@NoArgsConstructor //constructor bacio
@AllArgsConstructor //constructor con todos los argumento
@ToString //metodo tostring
public class Estudiante {

    @Id //para indicar que es una llava primaria en la base de datos.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para generar de manera automatica.
    private Integer idEstudiante;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

}
