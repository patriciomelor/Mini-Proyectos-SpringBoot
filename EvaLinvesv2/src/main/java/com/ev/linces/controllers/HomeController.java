package com.ev.linces.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Ruta inicial
    @GetMapping("/")
    public String index() {
        return "index"; // Renderiza la vista home
    }

    // Ruta para cliente
    @GetMapping("/cliente")
    public String cliente() {
        return "cliente"; // Renderiza la vista cliente
    }

    // Ruta para admins
    @GetMapping("/admins")
    public String admins() {
        return "admins"; // Renderiza la vista admins
    }
}