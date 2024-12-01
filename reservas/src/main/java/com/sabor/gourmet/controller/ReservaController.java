package com.sabor.gourmet.controller;

import com.sabor.gourmet.model.mesa;
import com.sabor.gourmet.model.reserva; // Importa la clase reserva del paquete model
import com.sabor.gourmet.repository.MesaRepository;
import com.sabor.gourmet.services.ReservaService; // Si estás usando el servicio en este controlador

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService ReservaService;

    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping
    public String listarreservas(Model model) {
        model.addAttribute("reservas", ReservaService.listarReservas());
        return "reservas/listar"; // Vista para listar reservas
    }

    @GetMapping("/crear")
    public String mostrarFormularioreserva(Model model) {
        model.addAttribute("reserva", new reserva());
        model.addAttribute("mesas", mesaRepository.findAll()); // Pasar mesas disponibles al formulario
        return "reservas/crear"; // Vista para crear reservas
    }

    @PostMapping("/crear")
    public String crearreserva(@RequestParam Long mesaId, @ModelAttribute reserva reserva) {
        mesa mesa = mesaRepository.findById(mesaId)
            .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        reserva.setMesa(mesa); // Relacionar la reserva con la mesa seleccionada
        ReservaService.crearReserva(reserva);
        return "redirect:/reservas"; // Redirigir a la lista de reservas
    }

    @PostMapping("/cancelar/{id}")
    public String cancelarreserva(@PathVariable Long id) {
        ReservaService.cancelarReserva(id);
        return "redirect:/reservas";
    }
}
