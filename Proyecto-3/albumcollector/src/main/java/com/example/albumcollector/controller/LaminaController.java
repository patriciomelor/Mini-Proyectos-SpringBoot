package com.example.albumcollector.controller;

import com.example.albumcollector.model.Album;
import com.example.albumcollector.model.Lamina;
import com.example.albumcollector.repository.LaminaRepository;
import com.example.albumcollector.repository.AlbumRepository;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/laminas")
public class LaminaController {
    @Autowired
    private LaminaRepository laminaRepository;
    @Autowired
    private AlbumRepository albumRepository;
    // Obtener todas las láminas
    @GetMapping
    public List<Lamina> getAllLaminas() {
        return laminaRepository.findAll();
    }
    // Obtener lámina por ID
    @GetMapping("/{id}")
    public ResponseEntity<Lamina> getLaminaById(@PathVariable Long id) {
        Lamina lamina = laminaRepository.findById(id)
                .orElseThrow(() -> new ResourceClosedException("Lámina no encontrada con ID: " + id));
        return ResponseEntity.ok(lamina);
    }
    // Crear una nueva lámina (asociada a un álbum)
    @PostMapping
    public Lamina createLamina(@Valid @RequestBody Lamina lamina) {
        if (lamina.getAlbum() == null || lamina.getAlbum().getId() == null) {
            throw new IllegalArgumentException("El álbum asociado es obligatorio.");
        }
        Album album = albumRepository.findById(lamina.getAlbum().getId())
                .orElseThrow(() -> new ResourceClosedException("Álbum no encontrado con ID: " + lamina.getAlbum().getId()));
        lamina.setAlbum(album);
        return laminaRepository.save(lamina);
    }
    // Actualizar lámina
    @PutMapping("/{id}")
    public ResponseEntity<Lamina> updateLamina(
            @PathVariable Long id,
            @Valid @RequestBody Lamina laminaDetails) {
        Lamina lamina = laminaRepository.findById(id)
                .orElseThrow(() -> new ResourceClosedException("Lámina no encontrada con ID: " + id));
        lamina.setNombre(laminaDetails.getNombre());
        lamina.setTipo(laminaDetails.getTipo());
        lamina.setEstado(laminaDetails.getEstado());
        Lamina updatedLamina = laminaRepository.save(lamina);
        return ResponseEntity.ok(updatedLamina);
    }
    // Eliminar lámina
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLamina(@PathVariable Long id) {
        Lamina lamina = laminaRepository.findById(id)
                .orElseThrow(() -> new ResourceClosedException("Lámina no encontrada con ID: " + id));
        laminaRepository.delete(lamina);
        return ResponseEntity.ok("Lámina eliminada correctamente.");
    }
}
