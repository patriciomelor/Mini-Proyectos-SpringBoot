package cl.ipss.apilincesgrupo09.repository;

import cl.ipss.apilincesgrupo09.models.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface profesorRepository extends MongoRepository<Profesor, Long> {

    boolean existsById(int profesorId);
}