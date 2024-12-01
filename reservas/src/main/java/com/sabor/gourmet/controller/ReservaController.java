package com.sabor.gourmet.controller;

import com.sabor.gourmet.model.reserva;
import com.sabor.gourmet.model.mesa;
import com.sabor.gourmet.services.ReservaService;
import com.sabor.gourmet.repository.MesaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que maneja las rutas y acciones para las reservas.
 */
@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ReservaService reservaService;

    /**
     * Muestra la lista de reservas.
     * @param model Modelo de la vista.
     * @return Vista de lista de reservas.
     */
    @GetMapping
    public String listarReservas(Model model) {
        List<reserva> reservas = reservaService.listarReservas();
        model.addAttribute("reservas", reservas);
        return "reservas/listar";
    }

    /**
     * Muestra el formulario para crear una nueva reserva.
     * @param model Modelo de la vista.
     * @return Vista del formulario de creación.
     */
    @GetMapping("/crear")
    public String mostrarFormularioReserva(Model model) {
        List<mesa> mesasDisponibles = mesaRepository.findByDisponible(true);
        model.addAttribute("mesas", mesasDisponibles);
        model.addAttribute("reserva", new reserva());
        return "reservas/crear";
    }

    /**
     * Crea una nueva reserva en el sistema.
     * @param reserva Objeto reserva con los datos ingresados.
     * @param mesaId ID de la mesa seleccionada.
     * @return Redirección a la lista de reservas.
     */
    @PostMapping("/crear")
    public String crearReserva(@ModelAttribute reserva reserva, @RequestParam Long mesaId) {
        mesa mesa = mesaRepository.findById(mesaId)
            .orElseThrow(() -> new RuntimeException("Mesa no encontrada con ID " + mesaId));
        reserva.setMesa(mesa);
        reservaService.crearReserva(reserva);
        return "redirect:/reservas";
    }

    /**
     * Cancela una reserva específica.
     * @param id ID de la reserva a cancelar.
     * @return Redirección a la lista de reservas.
     */
    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return "redirect:/reservas";
    }
}
