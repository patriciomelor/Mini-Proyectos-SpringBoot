package com.sabor.gourmet.Model;

import jakarta.persistence.*;

/**
 * Clase que representa una mesa en el sistema.
 * Mapea la tabla "mesa" en la base de datos.
 */
@Entity
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id;

    private int capacidad; // Capacidad máxima de la mesa
    private boolean disponible; // Estado de la mesa (disponible o ocupada)

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
