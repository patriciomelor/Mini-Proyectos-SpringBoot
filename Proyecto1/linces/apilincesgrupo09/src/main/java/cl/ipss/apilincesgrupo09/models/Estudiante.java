package cl.ipss.apilincesgrupo09.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "estudiantes")
public class Estudiante {

    @Id
    private long id;
    private String nombre;
    private String carrera;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public Estudiante(int id, String nombre, String carrera) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
    }
}

