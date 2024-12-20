package cl.ipss.apilincesgrupo09.models;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Jefedirecto") 
public class Jefedirecto {
    // Otros campos de la clase
    private String id;
    private String nombre;
    
    // Campo empresaId agregado
    private String empresaId;

    // Constructor que incluye el nuevo campo empresaId
    public Jefedirecto(String id, String nombre, String empresaId) {
        this.id = id;
        this.nombre = nombre;
        this.empresaId = empresaId;  // Asignación del campo empresaId
    }

    // Getters y setters para los campos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters para empresaId
    public String getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    // Método para mostrar la información si es necesario
    public void mostrarInformacion() {
        System.out.println("Jefe Directo: " + nombre + " | Empresa ID: " + empresaId);
    }
}


