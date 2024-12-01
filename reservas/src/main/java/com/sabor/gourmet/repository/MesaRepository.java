package com.sabor.gourmet.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sabor.gourmet.model.mesa;
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<mesa, Long> {
    List<mesa> findByDisponible(boolean disponible);
}
