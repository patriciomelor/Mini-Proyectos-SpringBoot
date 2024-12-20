package cl.ipss.apilincesgrupo09.repository;

import cl.ipss.apilincesgrupo09.models.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String> {

}

