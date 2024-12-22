package cl.ipss.apilincesgrupo09.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "estudiantes")
public class Estudiante {

    @Id
    private String id;
    private String nombre;
    private String carrera;
    public Estudiante(String id, String nombre, String carrera) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
    }
}

