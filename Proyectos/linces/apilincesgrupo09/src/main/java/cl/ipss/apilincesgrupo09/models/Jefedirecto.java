package cl.ipss.apilincesgrupo09.models;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
@Document(collection = "jefesdirectos")
public class Jefedirecto {

    @Id
    private long id;
    private String nombre;
  
}
