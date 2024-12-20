package cl.ipss.apilincesgrupo09.controllers;

import cl.ipss.apilincesgrupo09.models.Practica;
import cl.ipss.apilincesgrupo09.responses.PracticaResponse;
import cl.ipss.apilincesgrupo09.services.PracticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/practicas")
public class PracticaController {

    @Autowired
    private PracticaService practicaService;

    // Crear una nueva práctica
    @PostMapping
    public ResponseEntity<PracticaResponse> crearPractica(@RequestBody Practica practica) {
        Practica nuevaPractica = practicaService.agregarPractica(practica);
        PracticaResponse practicaResponse = new PracticaResponse();
        practicaResponse.setStatus(200);
        practicaResponse.setMessage("Práctica creada correctamente");
        practicaResponse.setPractica(nuevaPractica);
        return ResponseEntity.status(HttpStatus.CREATED).body(practicaResponse);
    }

    // Actualizar una práctica existente
    @PutMapping("/{id}")
    public ResponseEntity<PracticaResponse> actualizarPractica(@PathVariable String id, @RequestBody Practica practica) {
        Practica practicaActualizada = practicaService.actualizarPractica(id, practica);
        if (practicaActualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PracticaResponse());
        }
        PracticaResponse practicaResponse = new PracticaResponse();
        practicaResponse.setStatus(200);
        practicaResponse.setMessage("Práctica actualizada correctamente");
        practicaResponse.setPractica(practicaActualizada);
        return ResponseEntity.status(HttpStatus.OK).body(practicaResponse);
    }

    // Eliminar una práctica
    @DeleteMapping("/{id}")
    public ResponseEntity<PracticaResponse> eliminarPractica(@PathVariable String id) {
        boolean eliminado = practicaService.eliminarPractica(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PracticaResponse());
        }
        PracticaResponse practicaResponse = new PracticaResponse();
        practicaResponse.setStatus(200);
        practicaResponse.setMessage("Práctica eliminada correctamente");
        return ResponseEntity.status(HttpStatus.OK).body(practicaResponse);
    }
}
