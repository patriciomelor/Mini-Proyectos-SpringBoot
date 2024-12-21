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

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    // Crear o agregar una práctica
    public Practica agregarPractica(Practica practica) {
        practica.setId(String.valueOf(sequenceGeneratorService.generateSequence("practica_sequence")));
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

    // Actualizar una práctica
    public Practica actualizarPractica(String id, Practica practica) {
        Optional<Practica> practicaExistente = practicaRepository.findById(id);
        if (practicaExistente.isPresent()) {
            Practica practicaActualizada = practicaExistente.get();
            practicaActualizada.setFecha_inicio(practica.getFecha_inicio());
            practicaActualizada.setFecha_fin(practica.getFecha_fin());
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
