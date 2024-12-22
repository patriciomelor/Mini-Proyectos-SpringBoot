package cl.ipss.apilincesgrupo09.models;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
@Document(collection = "profesores")
public class Profesor {

    @Id
    private String id;
    private String nombre;
    private String especialidad;
}
