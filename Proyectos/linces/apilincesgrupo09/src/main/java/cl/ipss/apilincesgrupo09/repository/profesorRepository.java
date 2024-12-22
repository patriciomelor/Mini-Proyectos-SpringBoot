package cl.ipss.apilincesgrupo09.repository;

import cl.ipss.apilincesgrupo09.models.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface profesorRepository extends MongoRepository<Profesor, String> {
}
