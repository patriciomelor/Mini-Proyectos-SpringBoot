package cl.ipss.apilincesgrupo09.models;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Profesor") 

public class Profesor {
    private String nombre;
    private String especialidad;

    // Constructor
    public Profesor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Getter para el nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para la especialidad
    public String getEspecialidad() {
        return especialidad;
    }

    // Setter para la especialidad
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
