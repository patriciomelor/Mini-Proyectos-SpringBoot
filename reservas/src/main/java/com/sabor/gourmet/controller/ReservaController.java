package com.sabor.gourmet.controller;

import com.sabor.gourmet.model.reserva;
import com.sabor.gourmet.model.cliente;
import com.sabor.gourmet.model.mesa;
import com.sabor.gourmet.repository.MesaRepository;
import com.sabor.gourmet.services.ReservaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public String listarReservas(Model model) {
        List<reserva> reservas = reservaService.listarReservas(); // Ajustado el método correcto
        model.addAttribute("reservas", reservas);
        return "reservas/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioReserva(Model model) {
        List<mesa> mesasDisponibles = mesaRepository.findByDisponible(true);
        model.addAttribute("mesas", mesasDisponibles);
        model.addAttribute("reserva", new reserva());
        return "reservas/crear";
    }

    @PostMapping("/crear")
    public String crearReserva(@ModelAttribute reserva reserva, @RequestParam Long mesaId) {
        mesa mesa = mesaRepository.findById(mesaId)
            .orElseThrow(() -> new RuntimeException("Mesa no encontrada con ID " + mesaId));
        reserva.setMesa(mesa); // Asociar la mesa con la reserva
        reservaService.crearReserva(reserva);
        return "redirect:/reservas";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return "redirect:/reservas";
    }
}
