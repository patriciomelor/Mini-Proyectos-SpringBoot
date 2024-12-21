package cl.ipss.apilincesgrupo09.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import cl.ipss.apilincesgrupo09.models.Practica;

public interface PracticaRepository extends MongoRepository<Practica, String> {
}
