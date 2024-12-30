package com.example.albumcollector.controller;

import com.example.albumcollector.model.Lamina;
import com.example.albumcollector.repository.LaminaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/laminas")
public class LaminaController {

    @Autowired
    private LaminaRepository laminaRepository;

    @GetMapping
    public List<Lamina> getAllLaminas() {
        return laminaRepository.findAll();
    }

    @PostMapping
    public Lamina createLamina(@Valid @RequestBody Lamina lamina) {
        return laminaRepository.save(lamina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lamina> updateLamina(@PathVariable Long id, @Valid @RequestBody Lamina laminaDetails) {
        Lamina lamina = laminaRepository.findById(id).orElseThrow(() -> new RuntimeException("Lamina not found"));
        lamina.setNombre(laminaDetails.getNombre());
        lamina.setTipo(laminaDetails.getTipo());
        lamina.setEstado(laminaDetails.getEstado());
        final Lamina updatedLamina = laminaRepository.save(lamina);
        return ResponseEntity.ok(updatedLamina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLamina(@PathVariable Long id) {
        laminaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

