package com.sabor.gourmet.controller;

import com.sabor.gourmet.model.reserva;
import com.sabor.gourmet.model.mesa;
import com.sabor.gourmet.repository.MesaRepository;
import com.sabor.gourmet.services.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

  @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ReservaService ReservaService;

    @GetMapping
    public String listarreservas(Model model) {
        model.addAttribute("reservas", ReservaService.listarReservas());
        return "reservas/listar"; // Vista para listar reservas
    }

    @GetMapping("/crear")
    public String mostrarFormularioReserva(Model model) {
        List<mesa> mesasDisponibles = mesaRepository.findByDisponible(true);
        model.addAttribute("mesas", mesasDisponibles);
        model.addAttribute("reserva", new reserva());
        return "reservas/crear";
    }

    @PostMapping("/crear")
    public String crearReserva(reserva reserva) {
        ReservaService.crearReserva(reserva);
        return "redirect:/reservas";
    }
    

    @PostMapping("/cancelar/{id}")
    public String cancelarreserva(@PathVariable Long id) {
        ReservaService.cancelarReserva(id);
        return "redirect:/reservas";
    }
}
