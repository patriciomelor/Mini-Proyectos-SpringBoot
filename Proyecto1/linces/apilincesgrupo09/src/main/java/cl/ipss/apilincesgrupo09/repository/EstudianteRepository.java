package cl.ipss.apilincesgrupo09.repository;

import cl.ipss.apilincesgrupo09.models.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstudianteRepository extends MongoRepository<Estudiante, Integer> {
}
