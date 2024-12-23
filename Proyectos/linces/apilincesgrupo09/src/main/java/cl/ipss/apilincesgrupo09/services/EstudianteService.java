package cl.ipss.apilincesgrupo09.services;

import cl.ipss.apilincesgrupo09.models.Estudiante;
import cl.ipss.apilincesgrupo09.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    // Crear estudiante con ID autoincremental
    public Estudiante crearEstudiante(Estudiante estudiante) {
        estudiante.setId(sequenceGeneratorService.generateSequence("estudiantes_sequence"));
        return estudianteRepository.save(estudiante);
    }

    // Obtener todos los estudiantes
    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    // Obtener estudiante por ID
    public Optional<Estudiante> obtenerEstudiantePorId(int id) {
        return estudianteRepository.findById(id);
    }

    // Actualizar estudiante
    public Estudiante actualizarEstudiante(int id, Estudiante estudiante) {
        Optional<Estudiante> estudianteExistente = estudianteRepository.findById(id);
        if (estudianteExistente.isPresent()) {
            Estudiante actualizado = estudianteExistente.get();
            actualizado.setNombre(estudiante.getNombre());
            actualizado.setCarrera(estudiante.getCarrera());
            return estudianteRepository.save(actualizado);
        }
        return null;
    }

    // Eliminar estudiante
    public boolean eliminarEstudiante(int id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (estudiante.isPresent()) {
            estudianteRepository.delete(estudiante.get());
            return true;
        }
        return false;
    }
}
