package com.sabor.gourmet.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que representa una reserva en el sistema.
 * Mapea la tabla "reserva" en la base de datos.
 */
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id;

    @ManyToOne // Relación muchos-a-uno con la entidad "Mesa"
    @JoinColumn(name = "Mesa_id", nullable = false) // Clave foránea en la tabla "reserva"
    private Mesa Mesa;

    @ManyToOne // Relación muchos-a-uno con la entidad "Cliente"
    @JoinColumn(name = "Cliente_id", nullable = false) // Clave foránea en la tabla "reserva"
    private Cliente Cliente;

    private LocalDate fecha; // Fecha de la reserva
    private LocalTime hora; // Hora de la reserva
    private boolean activa; // Estado de la reserva (activa o cancelada)

    // Getters y setters para acceder y modificar los atributos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return Mesa;
    }

    public void setMesa(Mesa Mesa) {
        this.Mesa = Mesa;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
