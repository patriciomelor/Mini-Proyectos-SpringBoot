package cl.ipss.apilincesgrupo09.responses;

import cl.ipss.apilincesgrupo09.models.Practica;  // Asegúrate de importar la clase Practica correctamente
import lombok.Data;

@Data
public class PracticaResponse {

    private int status;  // Código de estado HTTP
    private String message;  // Mensaje de respuesta
    private Practica practica;  // Aquí guardamos la práctica creada o modificada

    // Métodos setters
    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    // Métodos getters
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Practica getPractica() {
        return practica;
    }
}
