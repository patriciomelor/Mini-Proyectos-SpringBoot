package cl.ipss.apilincesgrupo09.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counters")
public class Counter {
    
    @Id
    private String id;  // The name of the sequence (e.g., "practica_sequence")
    private int seq;    // The counter value

    // Getter for 'seq'
    public int getSeq() {
        return seq;
    }

    // Setter for 'seq'
    public void setSeq(int seq) {
        this.seq = seq;
    }

    // Getter for 'id'
    public String getId() {
        return id;
    }

    // Setter for 'id'
    public void setId(String id) {
        this.id = id;
    }
}
