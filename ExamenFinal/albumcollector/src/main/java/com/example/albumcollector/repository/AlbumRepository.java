package com.example.albumcollector.repository;

import com.example.albumcollector.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
