package cl.ipss.apilincesgrupo09.controllers;

import cl.ipss.apilincesgrupo09.models.Profesor;
import cl.ipss.apilincesgrupo09.repository.profesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private profesorRepository profesorRepository;

    @PostMapping
    public Profesor crearProfesor(@RequestBody Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @GetMapping
    public List<Profesor> obtenerProfesores() {
        return profesorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerProfesorPorId(@PathVariable String id) {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        return profesor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizarProfesor(@PathVariable String id, @RequestBody Profesor profesor) {
        if (!profesorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        profesor.setId(id);
        return ResponseEntity.ok(profesorRepository.save(profesor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable String id) {
        if (!profesorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        profesorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
