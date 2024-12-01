package com.sabor.gourmet.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que representa una reserva en el sistema.
 * Mapea la tabla "reserva" en la base de datos.
 */
@Entity
public class reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id;

    @ManyToOne // Relación muchos-a-uno con la entidad "mesa"
    @JoinColumn(name = "mesa_id", nullable = false) // Clave foránea en la tabla "reserva"
    private mesa mesa;

    @ManyToOne // Relación muchos-a-uno con la entidad "cliente"
    @JoinColumn(name = "cliente_id", nullable = false) // Clave foránea en la tabla "reserva"
    private cliente cliente;

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

    public mesa getMesa() {
        return mesa;
    }

    public void setMesa(mesa mesa) {
        this.mesa = mesa;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
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
