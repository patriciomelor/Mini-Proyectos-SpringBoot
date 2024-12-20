package cl.ipss.apilincesgrupo09.services;

import cl.ipss.apilincesgrupo09.repository.EstudianteRepository;
import cl.ipss.apilincesgrupo09.models.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService {

    // Inyección del repositorio EstudianteRepository
    @Autowired
    private EstudianteRepository estudianteRepository;

    // Método para agregar un nuevo estudiante
    public Estudiante agregarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);  // Guarda el estudiante en la base de datos
    }

    // Método para obtener un estudiante por su id
    public Estudiante obtenerEstudiantePorId(String id) {
        return estudianteRepository.findById(id).orElse(null);  // Devuelve el estudiante si se encuentra
    }
}
