package cl.ipss.apilincesgrupo09.services;
import cl.ipss.apilincesgrupo09.models.Practica;
import cl.ipss.apilincesgrupo09.repository.EstudianteRepository;
import cl.ipss.apilincesgrupo09.repository.PracticaRepository;
import cl.ipss.apilincesgrupo09.repository.profesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PracticaService {

    @Autowired
    private PracticaRepository practicaRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private profesorRepository profesorRepository;
    // Crear o agregar una práctica
    public Practica agregarPractica(Practica practica) {
            // Validar existencia de estudiante y profesor
        if (!estudianteRepository.existsById(practica.getEstudianteId())) {
            throw new RuntimeException("Estudiante no encontrado");
        }
        if (!profesorRepository.existsById(practica.getProfesorId())) {
            throw new RuntimeException("Profesor no encontrado");
        }
        practica.setId(sequenceGeneratorService.generateSequence("practicas_sequence"));
        return practicaRepository.save(practica);
    }

    // Obtener todas las prácticas
    public List<Practica> obtenerTodasPracticas() {
        return practicaRepository.findAll();
    }

    // Obtener una práctica por ID
    public Optional<Practica> obtenerPracticaPorId(String id) {
        return practicaRepository.findById(id);
    }

    // Actualizar una práctica existente
    public Practica actualizarPractica(String id, Practica practica) {
        // Buscar la práctica existente
        Optional<Practica> practicaExistente = practicaRepository.findById(id);
        
        // Si la práctica existe, actualiza sus campos
        if (practicaExistente.isPresent()) {
            Practica practicaActualizada = practicaExistente.get();
            practicaActualizada.setNombre_practica(practica.getNombre_practica());
            practicaActualizada.setEmpresa(practica.getEmpresa());
            practicaActualizada.setFecha_inicio(practica.getFecha_inicio());
            practicaActualizada.setFecha_fin(practica.getFecha_fin());
            practicaActualizada.setDescripcion(practica.getDescripcion());
            practicaActualizada.setJefeDirecto(practica.getJefeDirecto());
            
            // Guardar cambios
            return practicaRepository.save(practicaActualizada);
        }
        
        // Si no existe, devuelve null
        return null;
    }

    // Eliminar una práctica
    public boolean eliminarPractica(String id) {
        Optional<Practica> practicaExistente = practicaRepository.findById(id);
        if (practicaExistente.isPresent()) {
            practicaRepository.delete(practicaExistente.get());
            return true;
        }
        return false;
    }
}
