package com.example.albumcollector.controller;

import com.example.albumcollector.model.Album;
import com.example.albumcollector.repository.AlbumRepository;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;
    // Obtener todos los álbumes
    @GetMapping
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }
    // Obtener álbum por ID
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceClosedException("Álbum no encontrado con ID: " + id));
        return ResponseEntity.ok(album);
    }
    // Crear un nuevo álbum
    @PostMapping
    public Album createAlbum(@Valid @RequestBody Album album) {
        return albumRepository.save(album);
    }
    // Actualizar álbum existente
    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(
            @PathVariable Long id,
            @Valid @RequestBody Album albumDetails) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceClosedException("Álbum no encontrado con ID: " + id));
        album.setNombre(albumDetails.getNombre());
        album.setImagen(albumDetails.getImagen());
        album.setFechaLanzamiento(albumDetails.getFechaLanzamiento());
        Album updatedAlbum = albumRepository.save(album);
        return ResponseEntity.ok(updatedAlbum);
    }
    // Eliminar álbum
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceClosedException("Álbum no encontrado con ID: " + id));

        albumRepository.delete(album);
        return ResponseEntity.ok("Álbum eliminado correctamente.");
    }
}
