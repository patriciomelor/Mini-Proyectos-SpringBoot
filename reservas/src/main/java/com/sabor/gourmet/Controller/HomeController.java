package com.sabor.gourmet.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping("/")
    public String home() {
        // Puedes retornar el nombre de una vista (si usas Thymeleaf) 
        // o simplemente redirigir a otro controlador
        return "redirect:/reservas";
    }
}
