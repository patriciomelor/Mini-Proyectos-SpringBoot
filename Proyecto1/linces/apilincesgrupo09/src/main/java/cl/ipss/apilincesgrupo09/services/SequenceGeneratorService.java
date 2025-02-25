package cl.ipss.apilincesgrupo09.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import cl.ipss.apilincesgrupo09.models.Counter;

@Service
public class SequenceGeneratorService {

    private final MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    // Genera el siguiente valor de secuencia
    public long generateSequence(String seqName) {
        Query query = new Query(Criteria.where("_id").is(seqName));
        Update update = new Update().inc("sequence_value", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);

        Counter counter = mongoOperations.findAndModify(query, update, options, Counter.class);
        return counter != null ? counter.getSequence_value() : 1;
    }
}
