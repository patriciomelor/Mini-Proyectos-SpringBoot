package cl.ipss.apilincesgrupo09.controllers;

import cl.ipss.apilincesgrupo09.models.Practica;
import cl.ipss.apilincesgrupo09.responses.PracticaResponse;
import cl.ipss.apilincesgrupo09.services.PracticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/practicas")
public class PracticaController {

    @Autowired
    private PracticaService practicaService;

    // Crear una nueva práctica
    @PostMapping
    public ResponseEntity<PracticaResponse> crearPractica(@RequestBody Practica practica) {
        try {
            int estudianteId = Integer.parseInt(String.valueOf(practica.getEstudianteId()));
            int profesorId = Integer.parseInt(String.valueOf(practica.getProfesorId()));
    
            practica.setEstudianteId(estudianteId);
            practica.setProfesorId(profesorId);
    
            Practica nuevaPractica = practicaService.agregarPractica(practica);
            return construirRespuesta(nuevaPractica, "Práctica creada correctamente", HttpStatus.CREATED);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest()
                    .body(new PracticaResponse(HttpStatus.BAD_REQUEST.value(),
                            "EstudianteId o ProfesorId no son válidos", null));
        }
    }
    

    // Obtener todas las prácticas
    @GetMapping
    public ResponseEntity<List<Practica>> obtenerTodasPracticas() {
        List<Practica> practicas = practicaService.obtenerTodasPracticas();
        return ResponseEntity.ok(practicas);
    }

    // Obtener una práctica por ID
    @GetMapping("/{id}")
    public ResponseEntity<Practica> obtenerPracticaPorId(@PathVariable String id) {
        Optional<Practica> practica = practicaService.obtenerPracticaPorId(id);
        return practica.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Actualizar una práctica existente
    @PutMapping("/{id}")
    public ResponseEntity<PracticaResponse> actualizarPractica(@PathVariable String id, @RequestBody Practica practica) {
        Practica practicaActualizada = practicaService.actualizarPractica(id, practica);
        if (practicaActualizada == null) {
            return construirRespuesta(null, "Práctica no encontrada", HttpStatus.NOT_FOUND);
        }
        return construirRespuesta(practicaActualizada, "Práctica actualizada correctamente", HttpStatus.OK);
    }

    // Eliminar una práctica
    @DeleteMapping("/{id}")
    public ResponseEntity<PracticaResponse> eliminarPractica(@PathVariable String id) {
        boolean eliminado = practicaService.eliminarPractica(id);
        if (!eliminado) {
            return construirRespuesta(null, "Práctica no encontrada", HttpStatus.NOT_FOUND);
        }
        return construirRespuesta(null, "Práctica eliminada correctamente", HttpStatus.OK);
    }

    // Método para construir respuestas genéricas
    private ResponseEntity<PracticaResponse> construirRespuesta(Practica practica, String mensaje, HttpStatus status) {
        PracticaResponse practicaResponse = new PracticaResponse();
        practicaResponse.setStatus(status.value());
        practicaResponse.setMessage(mensaje);
        practicaResponse.setPractica(practica);
        return ResponseEntity.status(status).body(practicaResponse);
    }
}
