package com.example.albumcollector.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String imagen;
    private String fechaLanzamiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Lamina> laminas;

    
}
