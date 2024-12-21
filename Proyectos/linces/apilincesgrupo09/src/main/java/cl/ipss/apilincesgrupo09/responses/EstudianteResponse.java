package cl.ipss.apilincesgrupo09.responses;

import cl.ipss.apilincesgrupo09.models.Estudiante;

public class EstudianteResponse {
    private int status;
    private String message;
    private Estudiante estudiante;
    
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
