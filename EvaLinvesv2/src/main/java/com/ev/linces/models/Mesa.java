package com.ev.linces.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "mesa")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numeroMesa;

    private int capacidad;

    private boolean disponible;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mesa", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    public Mesa() {
    }
    
    public Mesa(int numeroMesa, int capacidad, boolean disponible) {
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.disponible = disponible;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
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

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public boolean verificarDisponibilidad(LocalDateTime fechaHoraReserva) {
        for (Reserva reserva : reservas) {
            if (reserva.getFechaHora().isBefore(fechaHoraReserva.plusMinutes(30)) && 
                reserva.getFechaHora().isAfter(fechaHoraReserva.minusMinutes(30))) {
                return false;  // Ya hay una reserva en ese rango
            }
        }
        return true;
    }


}