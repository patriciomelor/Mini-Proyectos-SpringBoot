package com.ev.linces.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.ev.linces.models.Mesa;
import com.ev.linces.services.MesaService;

@Controller
@RequestMapping("/mesas") // Base de las rutas para mesas
public class MesaController {

    @Autowired
    private MesaService mesaService;

    // Lista todas las mesas disponibles
    @GetMapping
    public String getAllMesas(Model model) {
        List<Mesa> todasLasMesas = mesaService.obtenerMesas(); // Obtiene todas las mesas
        
        model.addAttribute("mesas", todasLasMesas);
    
        return "mesas/mesasList"; // Vista: mesas/mesasList.html
    }

    // Ver detalles de una mesa específica
    @GetMapping("/{id}")
    public String obtenerMesaPorId(@PathVariable Long id, Model model) {
        Mesa mesa = mesaService.obtenerMesaPorId(id).orElse(null);
        if (mesa != null) {
            model.addAttribute("mesa", mesa);
            return "mesas/detail"; // Vista: mesas/detail.html (si existe)
        } else {
            return "redirect:/mesas"; // Redirige a la lista si no se encuentra
        }
    }

    // Formulario para crear una nueva mesa
    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "mesas/mesasForm"; // Vista: mesas/mesasForm.html
    }

    @PostMapping("/nuevo")
    public String createMesa(@ModelAttribute Mesa mesa) {
        mesaService.guardarMesa(mesa);
        return "redirect:/mesas"; // Redirige a la lista después de guardar
    }

    // Formulario para editar una mesa
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Mesa mesa = mesaService.obtenerMesaPorId(id).orElse(null);
        if (mesa != null) {
            model.addAttribute("mesa", mesa);
            return "mesas/mesasForm"; // Reutiliza el formulario para editar
        } else {
            return "redirect:/mesas"; // Redirige si no se encuentra la mesa
        }
    }

    @PostMapping("/editar/{id}")
    public String updateMesa(@PathVariable Long id, @ModelAttribute Mesa mesaDetails) {
        return mesaService.obtenerMesaPorId(id)
                .map(mesa -> {
                    mesa.setNumeroMesa(mesaDetails.getNumeroMesa());
                    mesa.setDisponible(mesaDetails.isDisponible());
                    mesaService.guardarMesa(mesa);
                    return "redirect:/mesas";
                })
                .orElse("redirect:/mesas");
    }

    // Eliminar una mesa
    @GetMapping("/eliminar/{id}")
    public String deleteMesa(@PathVariable Long id) {
        mesaService.deleteById(id);
        return "redirect:/mesas";
    }

    // Verificar disponibilidad de una mesa
    @GetMapping("/disponibilidad/{id}")
    public String verificarDisponibilidad(@PathVariable Long id, Model model) {
        boolean disponible = mesaService.verificarDisponibilidad(id);
        model.addAttribute("disponible", disponible);
        return "mesas/disponibilidad"; // Vista: mesas/disponibilidad.html (si existe)
    }
}