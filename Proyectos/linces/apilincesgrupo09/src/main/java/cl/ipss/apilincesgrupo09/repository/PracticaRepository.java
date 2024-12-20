package cl.ipss.apilincesgrupo09.repository;


import cl.ipss.apilincesgrupo09.models.Practica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticaRepository extends MongoRepository<Practica, String> {
   
}
