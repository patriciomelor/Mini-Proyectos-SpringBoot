package com.sabor.gourmet.model;

import jakarta.persistence.*;

/**
 * Clase que representa un cliente en el sistema.
 * Mapea la tabla "cliente" en la base de datos.
 */
@Entity
public class cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id;

    private String email; // Correo electrónico del cliente
    private String nombre; // Nombre del cliente
    private String telefono; // Teléfono del cliente

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
