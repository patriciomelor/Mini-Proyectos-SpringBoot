package cl.ipss.apilincesgrupo09.services;

import cl.ipss.apilincesgrupo09.models.Practica;
import cl.ipss.apilincesgrupo09.repository.PracticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PracticaService {

    @Autowired
    private PracticaRepository practicaRepository;

    // Crear o agregar una práctica
    public Practica agregarPractica(Practica practica) {
        return practicaRepository.save(practica);
    }

    // Obtener todas las prácticas
    public List<Practica> obtenerTodasPracticas() {
        return practicaRepository.findAll();
    }

    // Actualizar una práctica
    public Practica actualizarPractica(String id, Practica practica) {
        Optional<Practica> practicaExistente = practicaRepository.findById(id);
        if (practicaExistente.isPresent()) {
            Practica practicaActualizada = practicaExistente.get();
            practicaActualizada.setFechaInicio(practica.getFechaInicio());
            practicaActualizada.setFechaTermino(practica.getFechaTermino());
            practicaActualizada.setDescripcion(practica.getDescripcion());
            practicaActualizada.setEmpresa(practica.getEmpresa());
            practicaActualizada.setJefeDirecto(practica.getJefeDirecto());
            return practicaRepository.save(practicaActualizada);
        }
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
