package com.sabor.gourmet.model;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.*;

@Entity
public class reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private cliente cliente;

    @ManyToOne
    private mesa mesa;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    private boolean activa;

    @Transient // Indica que no se persiste en la base de datos
    private String fechaFormatted;

    public String getFechaFormatted() {
        return fechaFormatted;
    }
    
    public void setFechaFormatted(String fechaFormatted) {
        this.fechaFormatted = fechaFormatted;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    public mesa getMesa() {
        return mesa;
    }

    public void setMesa(mesa mesa) {
        this.mesa = mesa;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
