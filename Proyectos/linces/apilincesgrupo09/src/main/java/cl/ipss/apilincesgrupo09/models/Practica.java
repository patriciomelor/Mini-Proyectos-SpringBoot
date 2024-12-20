package cl.ipss.apilincesgrupo09.models;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Practica") 

public class Practica {
    private String id;
    private Date fechaInicio;
    private Date fechaTermino;
    private String descripcion;
    private String empresa;  // Nuevo campo agregado
    private String jefeDirecto;  // Nuevo campo agregado

    // Constructor
    public Practica(String id, Date fechaInicio, Date fechaTermino, String descripcion, String empresa, String jefeDirecto) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.descripcion = descripcion;
        this.empresa = empresa;
        this.jefeDirecto = jefeDirecto;
    }

    // Métodos getter y setter para todos los campos

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmpresa() {  // Getter para empresa
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getJefeDirecto() {  // Getter para jefeDirecto
        return jefeDirecto;
    }

    public void setJefeDirecto(String jefeDirecto) {
        this.jefeDirecto = jefeDirecto;
    }
}
