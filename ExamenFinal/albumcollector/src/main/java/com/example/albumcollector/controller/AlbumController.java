package com.example.albumcollector.controller;

import com.example.albumcollector.model.Album;
import com.example.albumcollector.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        return albumRepository.save(album);
    }

    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable Long id, @RequestBody Album albumDetails) {
        Album album = albumRepository.findById(id).orElseThrow();
        album.setNombre(albumDetails.getNombre());
        album.setImagen(albumDetails.getImagen());
        album.setFechaLanzamiento(albumDetails.getFechaLanzamiento());
        return albumRepository.save(album);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable Long id) {
        albumRepository.deleteById(id);
    }
}
