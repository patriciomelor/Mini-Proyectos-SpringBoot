package com.sabor.gourmet.Model;

import jakarta.persistence.*;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Mesa Mesa;

    private boolean activa;

    public Mesa getMesa() {
        return Mesa;
    }

    public void setMesa(Mesa Mesa) {
        this.Mesa = Mesa;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    // Getters y setters
}
