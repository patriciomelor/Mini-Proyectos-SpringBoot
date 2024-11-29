package com.sabor.gourmet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sabor.gourmet.Model.Mesa;
import com.sabor.gourmet.Model.Reserva;
import com.sabor.gourmet.Services.ReservaService;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/reservas")
public class ReservaController {
    @SuppressWarnings("rawtypes")
    @Autowired
    private ReservaService ReservaService;

    @GetMapping
    public String listarReservas(Model model) {
        model.addAttribute("reservas", ReservaService.listarReservas());
        return "reservas/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        return "reservas/crear";
    }

    @PostMapping("/crear")
    public String crearReserva(@ModelAttribute Reserva reserva) {
        ReservaService.crearReserva(reserva);
        return "redirect:/reservas";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id) {
        ReservaService.cancelarReserva(id);
        return "redirect:/reservas";
    }
}
