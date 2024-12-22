package cl.ipss.apilincesgrupo09.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import lombok.Data;

@Data
@Document(collection = "practicas")
public class Practica {

    @Id
    private String id;
    private String nombre_practica;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String descripcion;

    // Relaciones
    private String estudianteId;
    private String empresaId;
    private String jefeDirectoId;
    private String profesorSupervisorId;
    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_practica() {
        return nombre_practica;
    }

    public void setNombre_practica(String nombre_practica) {
        this.nombre_practica = nombre_practica;
    }

    public String getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getJefeDirectoId() {
        return jefeDirectoId;
    }

    public void setJefeDirectoId(String jefeDirectoId) {
        this.jefeDirectoId = jefeDirectoId;
    }
}
