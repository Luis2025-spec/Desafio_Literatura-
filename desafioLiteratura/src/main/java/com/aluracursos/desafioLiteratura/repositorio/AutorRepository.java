package com.aluracursos.desafioLiteratura.repositorio;

import com.aluracursos.desafioLiteratura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByFechaNacimientoLessThanEqualAndFechaFallecimientoIsNullOrFechaFallecimientoGreaterThanEqual(
            LocalDate fechaNacimiento,
            LocalDate fechaFallecimiento
    );

}

