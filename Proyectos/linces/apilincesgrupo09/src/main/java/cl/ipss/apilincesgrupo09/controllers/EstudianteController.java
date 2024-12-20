package cl.ipss.apilincesgrupo09.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cl.ipss.apilincesgrupo09.models.Estudiante;
import cl.ipss.apilincesgrupo09.services.EstudianteService;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Método para agregar un estudiante
    @PostMapping("/agregar")
    public Estudiante agregarEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.agregarEstudiante(estudiante);
    }

    // Otros métodos del controlador si es necesario
}
