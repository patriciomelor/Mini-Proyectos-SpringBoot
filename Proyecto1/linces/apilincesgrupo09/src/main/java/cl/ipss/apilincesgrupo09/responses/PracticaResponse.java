package cl.ipss.apilincesgrupo09.responses;

import cl.ipss.apilincesgrupo09.models.Practica;

public class PracticaResponse {
    private int status;
    private String message;
    private Practica practica;

    // Constructor que genera el error
    public PracticaResponse(int i, String string, Object object) {
        this.status = status;
        this.message = message;
        this.practica = practica;
    }
    public PracticaResponse() {
        // Constructor vacío
    }
    // Getters y Setters
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

    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }
}
