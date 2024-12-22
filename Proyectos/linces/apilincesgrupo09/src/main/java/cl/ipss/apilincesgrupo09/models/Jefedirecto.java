package cl.ipss.apilincesgrupo09.models;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
@Document(collection = "jefesdirectos")
public class Jefedirecto {

    @Id
    private String id;
    private String nombre;
    public Jefedirecto(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
