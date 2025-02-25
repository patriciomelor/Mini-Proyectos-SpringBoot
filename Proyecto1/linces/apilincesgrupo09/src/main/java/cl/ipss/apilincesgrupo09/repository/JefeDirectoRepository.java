package cl.ipss.apilincesgrupo09.repository;


import cl.ipss.apilincesgrupo09.models.Jefedirecto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JefeDirectoRepository extends MongoRepository<Jefedirecto, Long> {
    
}
