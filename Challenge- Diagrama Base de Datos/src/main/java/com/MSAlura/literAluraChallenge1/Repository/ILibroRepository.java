package com.MSAlura.literAluraChallenge1.Repository;

import com.MSAlura.literAluraChallenge1.Modelos.Tables.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ILibroRepository extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
    List<Libro> findLibroByIdiomas(String idiomas);
}

