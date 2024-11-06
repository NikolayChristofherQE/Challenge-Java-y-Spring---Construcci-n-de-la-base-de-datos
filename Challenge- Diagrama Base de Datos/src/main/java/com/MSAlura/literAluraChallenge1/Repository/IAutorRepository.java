package com.MSAlura.literAluraChallenge1.Repository;

import com.MSAlura.literAluraChallenge1.Modelos.Tables.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface IAutorRepository  extends JpaRepository<Autor,Long> {
    @Query("SELECT a FROM Libro l JOIN l.autores a WHERE a.nombre ILIKE %:nombreAutor%")
    List<Autor> autoresPorNombre(String nombreAutor);

    List<Autor> findByFechaDeNacimientoLessThanEqualAndFechaDefallecimientoGreaterThanEqualOrFechaDefallecimientoIsNull(Integer finSiglo, Integer inicioSiglo);
}
