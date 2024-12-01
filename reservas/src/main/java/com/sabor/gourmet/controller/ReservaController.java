package com.sabor.gourmet.controller;

import com.sabor.gourmet.model.Reserva; // Importa la clase Reserva del paquete model
import com.sabor.gourmet.services.ReservaService; // Si estás usando el servicio en este controlador

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public String listarReservas(Model model) {
        model.addAttribute("reservas", reservaService.listarReservas());
        return "reservas/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        return "reservas/crear";
    }

    @PostMapping("/crear")
    public String crearReserva(@ModelAttribute Reserva reserva) {
        reservaService.crearReserva(reserva);
        return "redirect:/reservas";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return "redirect:/reservas";
    }
}
